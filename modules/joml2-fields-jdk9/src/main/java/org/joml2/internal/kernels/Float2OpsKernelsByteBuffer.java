// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2OpsKernelsByteBuffer {
    private Float2OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, otherX + _selfx);
        dest.putFloat(destOffset + 4, otherY + _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, _otherx + _selfx);
        dest.putFloat(destOffset + 4, _othery + _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx / scalar);
        dest.putFloat(destOffset + 4, _selfy / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx / otherX);
        dest.putFloat(destOffset + 4, _selfy / otherY);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, _selfx / _otherx);
        dest.putFloat(destOffset + 4, _selfy / _othery);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_selfx, b, cX));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _cx = c.getFloat(cOffset + 0);
        float _cy = c.getFloat(cOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_selfx, b, _cx));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_selfx, bX, cX));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _bx = b.getFloat(bOffset + 0);
        float _by = b.getFloat(bOffset + 4);
        float _cx = c.getFloat(cOffset + 0);
        float _cy = c.getFloat(cOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _bx, _cx));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, scalar * _selfx);
        dest.putFloat(destOffset + 4, scalar * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, otherX * _selfx);
        dest.putFloat(destOffset + 4, otherY * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, _otherx * _selfx);
        dest.putFloat(destOffset + 4, _othery * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, -_selfx);
        dest.putFloat(destOffset + 4, -_selfy);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx - otherX);
        dest.putFloat(destOffset + 4, _selfy - otherY);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, _selfx - _otherx);
        dest.putFloat(destOffset + 4, _selfy - _othery);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, float u) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, float u) {
        float _t0 = u * 6.2831855f;
        float _t1 = (float) Math.sin(_t0);
        dest.putFloat(destOffset + 0, (float) Math.cosFromSin(_t1, _t0));
        dest.putFloat(destOffset + 4, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset + 0, vX);
        dest.putFloat(destOffset + 4, vY);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, _vx);
        dest.putFloat(destOffset + 4, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset + 0, s);
        dest.putFloat(destOffset + 4, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset + 0, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.putFloat(destOffset + 4, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p3x = p3.getFloat(p3Offset + 0);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset + 0, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.putFloat(destOffset + 4, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset + 0, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset + 0, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset + 0, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putFloat(destOffset + 4, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset + 0, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset + 0, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p3x = p3.getFloat(p3Offset + 0);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset + 0, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset + 0, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.putFloat(destOffset + 4, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p3x = p3.getFloat(p3Offset + 0);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset + 0, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.putFloat(destOffset + 4, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = t * t;
        dest.putFloat(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putFloat(destOffset + 4, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _p1x = p1.getFloat(p1Offset + 0);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p2x = p2.getFloat(p2Offset + 0);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p3x = p3.getFloat(p3Offset + 0);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _t0 = t * t;
        dest.putFloat(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.putFloat(destOffset + 4, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0x = t0.getFloat(t0Offset + 0);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _v1x = v1.getFloat(v1Offset + 0);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _t1x = t1.getFloat(t1Offset + 0);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0x = t0.getFloat(t0Offset + 0);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _v1x = v1.getFloat(v1Offset + 0);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _t1x = t1.getFloat(t1Offset + 0);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float tX, float tY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(tX, otherX - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _tx = t.getFloat(tOffset + 0);
        float _ty = t.getFloat(tOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t7 = (1.0f / (float) Math.sqrt(_t5));
        float _t10 = (1.0f / (float) Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t20 = t * (float) Math.sqrt(_t6) + (1.0f - t) * (float) Math.sqrt(_t5);
        float _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t39 = t * (float) Math.atan2((float) Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * (float) Math.sin(_t39) * (1.0f / (float) Math.sqrt(_t35));
        float _t44 = _t20 * (float) Math.cos(_t39);
        dest.putFloat(destOffset + 0, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.putFloat(destOffset + 4, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return Float2OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t11_inv = 1.0f / _t11;
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) Math.sqrt(_t18));
        float _t23 = (1.0f / (float) Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t28 = -_t27;
        float _t37 = t * (float) Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) Math.sqrt(_t19) * (_t11 / _t2);
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) Math.sqrt(_t60));
        float _t64 = t * (float) Math.atan2((float) Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * (float) Math.sin(_t64);
        float _t69 = _t37 * (float) Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset + 0, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.putFloat(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.putFloat(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.putFloat(destOffset + 4, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.putFloat(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t7 = (1.0f / (float) Math.sqrt(_t5));
        float _t10 = (1.0f / (float) Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t20 = t * (float) Math.sqrt(_t6) + (1.0f - t) * (float) Math.sqrt(_t5);
        float _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t39 = t * (float) Math.atan2((float) Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * (float) Math.sin(_t39) * (1.0f / (float) Math.sqrt(_t35));
        float _t44 = _t20 * (float) Math.cos(_t39);
        dest.putFloat(destOffset + 0, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.putFloat(destOffset + 4, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float2OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t11_inv = 1.0f / _t11;
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) Math.sqrt(_t18));
        float _t23 = (1.0f / (float) Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t28 = -_t27;
        float _t37 = t * (float) Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) Math.sqrt(_t19) * (_t11 / _t2);
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) Math.sqrt(_t60));
        float _t64 = t * (float) Math.atan2((float) Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * (float) Math.sin(_t64);
        float _t69 = _t37 * (float) Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset + 0, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.putFloat(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.putFloat(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.putFloat(destOffset + 4, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.putFloat(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
            dest.putFloat(destOffset + 4, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.abs(_selfx));
        dest.putFloat(destOffset + 4, Math.abs(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.acos(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.acos(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(scalar, bX, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _bx = b.getFloat(bOffset + 0);
        float _by = b.getFloat(bOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(scalar, _bx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(bX, cX, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _bx = b.getFloat(bOffset + 0);
        float _by = b.getFloat(bOffset + 4);
        float _cx = c.getFloat(cOffset + 0);
        float _cy = c.getFloat(cOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_bx, _cx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _ct0 = Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY);
        return (float) Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return (float) Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _ct0 = Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return (float) Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return (float) Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.asin(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.asin(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.atan(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.atan(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.atan2(_selfx, x));
        dest.putFloat(destOffset + 4, (float) Math.atan2(_selfy, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.atan2(_selfx, xX));
        dest.putFloat(destOffset + 4, (float) Math.atan2(_selfy, xY));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _xx = x.getFloat(xOffset + 0);
        float _xy = x.getFloat(xOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.atan2(_selfx, _xx));
        dest.putFloat(destOffset + 4, (float) Math.atan2(_selfy, _xy));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.cbrt(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.cbrt(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(Math.max(_selfx, min), max));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float maxX, float maxY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float maxX, float maxY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(Math.max(_selfx, minX), maxX));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _minx = min.getFloat(minOffset + 0);
        float _miny = min.getFloat(minOffset + 4);
        float _maxx = max.getFloat(maxOffset + 0);
        float _maxy = max.getFloat(maxOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static float compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return _selfx + _selfy;
    }

    public static float compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.max(_selfx, _selfy);
    }

    public static float compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.min(_selfx, _selfy);
    }

    public static float compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return _selfx * _selfy;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.copySign(_selfx, sign));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.copySign(_selfx, signX));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, signY));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _signx = sign.getFloat(signOffset + 0);
        float _signy = sign.getFloat(signOffset + 4);
        dest.putFloat(destOffset + 0, Math.copySign(_selfx, _signx));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.cos(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.cos(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.cosh(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.cosh(_selfy));
        return dest;
    }

    public static float cross_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, otherX, otherY);
    }

    public static float cross_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.fma(otherY, _selfx, -(otherX * _selfy));
    }

    public static float cross_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, _otherBase);
    }

    public static float cross_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        return Math.fma(_othery, _selfx, -(_otherx * _selfy));
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.toDegrees(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.toDegrees(_selfy));
        return dest;
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = _selfx - otherX;
        float _t1 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t0 = _selfx - _otherx;
        float _t1 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = _selfx - otherX;
        float _t1 = _selfy - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t0 = _selfx - _otherx;
        float _t1 = _selfy - _othery;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.fma(otherX, _selfx, otherY * _selfy);
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        return Math.fma(_otherx, _selfx, _othery * _selfy);
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.exp(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.exp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.pow(2.0f, _selfx));
        dest.putFloat(destOffset + 4, (float) Math.pow(2.0f, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.expm1(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.expm1(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float NrefX, float NrefY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float NrefX, float NrefY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        if (Math.fma(IX, NrefX, IY * NrefY) < 0.0f) {
            dest.putFloat(destOffset + 0, _selfx);
            dest.putFloat(destOffset + 4, _selfy);
        } else {
            dest.putFloat(destOffset + 0, -_selfx);
            dest.putFloat(destOffset + 4, -_selfy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _Ix = I.getFloat(IOffset + 0);
        float _Iy = I.getFloat(IOffset + 4);
        float _Nrefx = Nref.getFloat(NrefOffset + 0);
        float _Nrefy = Nref.getFloat(NrefOffset + 4);
        if (Math.fma(_Ix, _Nrefx, _Iy * _Nrefy) < 0.0f) {
            dest.putFloat(destOffset + 0, _selfx);
            dest.putFloat(destOffset + 4, _selfy);
        } else {
            dest.putFloat(destOffset + 0, -_selfx);
            dest.putFloat(destOffset + 4, -_selfy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.floor(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.floor(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f));
        dest.putFloat(destOffset + 4, Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.hypot(_selfx, y));
        dest.putFloat(destOffset + 4, (float) Math.hypot(_selfy, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.hypot(_selfx, yX));
        dest.putFloat(destOffset + 4, (float) Math.hypot(_selfy, yY));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _yx = y.getFloat(yOffset + 0);
        float _yy = y.getFloat(yOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.hypot(_selfx, _yx));
        dest.putFloat(destOffset + 4, (float) Math.hypot(_selfy, _yy));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, 1.0f / _selfx);
        dest.putFloat(destOffset + 4, 1.0f / _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (1.0f / (float) Math.sqrt(_selfx)));
        dest.putFloat(destOffset + 4, (1.0f / (float) Math.sqrt(_selfy)));
        return dest;
    }

    public static float length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return (float) Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static float lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.log(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.log(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.log10(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.log10(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.log1p(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.log1p(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.log2(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.log2(_selfy));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY);
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery);
    }

    public static float manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        return Math.abs(_selfx) + Math.abs(_selfy);
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.max(_selfx, scalar));
        dest.putFloat(destOffset + 4, Math.max(_selfy, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.max(_selfx, otherX));
        dest.putFloat(destOffset + 4, Math.max(_selfy, otherY));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, Math.max(_selfx, _otherx));
        dest.putFloat(destOffset + 4, Math.max(_selfy, _othery));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(_selfx, scalar));
        dest.putFloat(destOffset + 4, Math.min(_selfy, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(_selfx, otherX));
        dest.putFloat(destOffset + 4, Math.min(_selfy, otherY));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        dest.putFloat(destOffset + 0, Math.min(_selfx, _otherx));
        dest.putFloat(destOffset + 4, Math.min(_selfy, _othery));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, flooredMod(_selfx, y));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, flooredMod(_selfx, yX));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, yY));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _yx = y.getFloat(yOffset + 0);
        float _yy = y.getFloat(yOffset + 4);
        dest.putFloat(destOffset + 0, flooredMod(_selfx, _yx));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, _yy));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.nextDown(_selfx));
        dest.putFloat(destOffset + 4, Math.nextDown(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.nextUp(_selfx));
        dest.putFloat(destOffset + 4, Math.nextUp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t2 = (1.0f / (float) Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.putFloat(destOffset + 0, _selfx * _t2);
            dest.putFloat(destOffset + 4, _selfy * _t2);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t3 = length * (1.0f / (float) Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.putFloat(destOffset + 0, _selfx * _t3);
            dest.putFloat(destOffset + 4, _selfy * _t3);
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _ct0 = Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY);
        return (float) Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = (float) Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static float orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _ct0 = Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset);
        return (float) Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _otherx = other.getFloat(otherOffset + 0);
        float _othery = other.getFloat(otherOffset + 4);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = (float) Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, rowX * _selfx);
        dest.putFloat(destOffset + 4, rowX * _selfy);
        dest.putFloat(destOffset + 8, rowY * _selfx);
        dest.putFloat(destOffset + 12, rowY * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _rowx = row.getFloat(rowOffset + 0);
        float _rowy = row.getFloat(rowOffset + 4);
        dest.putFloat(destOffset + 0, _rowx * _selfx);
        dest.putFloat(destOffset + 4, _rowx * _selfy);
        dest.putFloat(destOffset + 8, _rowy * _selfx);
        dest.putFloat(destOffset + 12, _rowy * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.pow(_selfx, exponent));
        dest.putFloat(destOffset + 4, (float) Math.pow(_selfy, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.pow(_selfx, exponentX));
        dest.putFloat(destOffset + 4, (float) Math.pow(_selfy, exponentY));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _exponentx = exponent.getFloat(exponentOffset + 0);
        float _exponenty = exponent.getFloat(exponentOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.pow(_selfx, _exponentx));
        dest.putFloat(destOffset + 4, (float) Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t5 = Math.fma(ontoX, _selfx, ontoY * _selfy) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dest.putFloat(destOffset + 0, ontoX * _t5);
        dest.putFloat(destOffset + 4, ontoY * _t5);
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _ontox = onto.getFloat(ontoOffset + 0);
        float _ontoy = onto.getFloat(ontoOffset + 4);
        float _t5 = Math.fma(_ontox, _selfx, _ontoy * _selfy) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        dest.putFloat(destOffset + 0, _ontox * _t5);
        dest.putFloat(destOffset + 4, _ontoy * _t5);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        dest.putFloat(destOffset + 0, Math.fma(-normalX, _t1, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _normalx = normal.getFloat(normalOffset + 0);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.putFloat(destOffset + 0, Math.fma(-_normalx, _t1, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.toRadians(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.toRadians(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t2 = 2.0f * Math.fma(normalX, _selfx, normalY * _selfy);
        dest.putFloat(destOffset + 0, Math.fma(-normalX, _t2, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _normalx = normal.getFloat(normalOffset + 0);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _t2 = 2.0f * Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.putFloat(destOffset + 0, Math.fma(-_normalx, _t2, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float eta) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) Math.sqrt(Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.putFloat(destOffset + 0, Math.fma(eta, _selfx, -(normalX * _t9)));
            dest.putFloat(destOffset + 4, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _normalx = normal.getFloat(normalOffset + 0);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) Math.sqrt(Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.putFloat(destOffset + 0, Math.fma(eta, _selfx, -(_normalx * _t9)));
            dest.putFloat(destOffset + 4, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            dest.putFloat(destOffset + 0, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.rint(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.rint(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.signum(_selfx));
        dest.putFloat(destOffset + 4, Math.signum(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.sin(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.sin(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.sinh(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.sinh(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t7 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, _t7, 3.0f) * _t7 * _t7);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _edge0x = edge0.getFloat(edge0Offset + 0);
        float _edge0y = edge0.getFloat(edge0Offset + 4);
        float _edge1x = edge1.getFloat(edge1Offset + 0);
        float _edge1y = edge1.getFloat(edge1Offset + 4);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        dest.putFloat(destOffset + 0, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.sqrt(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.sqrt(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx < edgeX ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edgeY ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _edgex = edge.getFloat(edgeOffset + 0);
        float _edgey = edge.getFloat(edgeOffset + 4);
        dest.putFloat(destOffset + 0, _selfx < _edgex ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < _edgey ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.tan(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.tan(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, (float) Math.tanh(_selfx));
        dest.putFloat(destOffset + 4, (float) Math.tanh(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        dest.putFloat(destOffset + 0, Math.ulp(_selfx));
        dest.putFloat(destOffset + 4, Math.ulp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 8);
        float _mat11 = mat.getFloat(matOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMulDirectionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 8);
        float _mat11 = mat.getFloat(matOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMulDirectionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 12);
        float _mat11 = mat.getFloat(matOffset + 16);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat03 = mat.getFloat(matOffset + 48);
        float _mat13 = mat.getFloat(matOffset + 52);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat03)));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 8);
        float _mat11 = mat.getFloat(matOffset + 12);
        float _mat02 = mat.getFloat(matOffset + 16);
        float _mat12 = mat.getFloat(matOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _mat00 = mat.getFloat(matOffset + 0);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat01 = mat.getFloat(matOffset + 12);
        float _mat11 = mat.getFloat(matOffset + 16);
        float _mat02 = mat.getFloat(matOffset + 24);
        float _mat12 = mat.getFloat(matOffset + 28);
        dest.putFloat(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfy - pivotY;
        dest.putFloat(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.getFloat(srcOffset + 0);
        float _selfy = src.getFloat(srcOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfy - _pivoty;
        dest.putFloat(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
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
