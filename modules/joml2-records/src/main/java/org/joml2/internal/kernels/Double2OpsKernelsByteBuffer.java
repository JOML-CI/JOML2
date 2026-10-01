// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2OpsKernelsByteBuffer {
    private Double2OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, otherX + _selfx);
        dest.putDouble(destOffset + 8, otherY + _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, _otherx + _selfx);
        dest.putDouble(destOffset + 8, _othery + _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx / scalar);
        dest.putDouble(destOffset + 8, _selfy / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx / otherX);
        dest.putDouble(destOffset + 8, _selfy / otherY);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, _selfx / _otherx);
        dest.putDouble(destOffset + 8, _selfy / _othery);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(_selfx, b, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        dest.putDouble(destOffset, Math.fma(_selfx, b, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(_selfx, bX, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        dest.putDouble(destOffset, Math.fma(_selfx, _bx, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, scalar * _selfx);
        dest.putDouble(destOffset + 8, scalar * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, otherX * _selfx);
        dest.putDouble(destOffset + 8, otherY * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, _otherx * _selfx);
        dest.putDouble(destOffset + 8, _othery * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, -_selfx);
        dest.putDouble(destOffset + 8, -_selfy);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx - otherX);
        dest.putDouble(destOffset + 8, _selfy - otherY);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, _selfx - _otherx);
        dest.putDouble(destOffset + 8, _selfy - _othery);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, double u) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, double u) {
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, Math.cosFromSin(_t1, _t0));
        dest.putDouble(destOffset + 8, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2OpsKernelsAddress.set_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, vY);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.putDouble(destOffset + 8, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.putDouble(destOffset + 8, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = t * t;
        dest.putDouble(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _t0 = t * t;
        dest.putDouble(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0x = t0.getDouble(t0Offset);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _v1x = v1.getDouble(v1Offset);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _t1x = t1.getDouble(t1Offset);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0x = t0.getDouble(t0Offset);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _v1x = v1.getDouble(v1Offset);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _t1x = t1.getDouble(t1Offset);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double tX, double tY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        dest.putDouble(destOffset, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        double _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        return slerp_api_s35308f7e_1(dest, destOffset, src, srcOffset, otherX, otherY, t, _t12, _t16, t * java.lang.Math.sqrt(_t6) + (1.0 - t) * java.lang.Math.sqrt(_t5), _t21, _t31, _t32, Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_s35308f7e_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t, double _t12, double _t16, double _t20, double _t21, double _t31, double _t32, double _t35) {
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t39 = t * Math.atan2(java.lang.Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / java.lang.Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        dest.putDouble(destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.putDouble(destOffset + 8, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return Double2OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = java.lang.Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_api_s1ff61333_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_s1ff61333_1(java.nio.ByteBuffer dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49, double _t51) {
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_api_s1ff61333_2(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_s1ff61333_2(java.nio.ByteBuffer dest, int destOffset, double otherX, double otherY, double t, double _selfx, double _selfy, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t38, double _t48, double _t49, double _t68, double _t69, double _t72, double _t73) {
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest.putDouble(destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.putDouble(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.putDouble(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.putDouble(destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        double _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        return slerp_api_seb057073_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t12, _t16, t * java.lang.Math.sqrt(_t6) + (1.0 - t) * java.lang.Math.sqrt(_t5), _t21, _t31, _t32, Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_seb057073_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t, double _t12, double _t16, double _t20, double _t21, double _t31, double _t32, double _t35) {
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t39 = t * Math.atan2(java.lang.Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / java.lang.Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        dest.putDouble(destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.putDouble(destOffset + 8, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = java.lang.Math.min(_t2, _t1);
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / java.lang.Math.sqrt(_t18));
        double _t23 = (1.0 / java.lang.Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_api_s68ff79b2_1(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0 / _t11, _t18, _t19, _t25, _t27, -_t27, t * java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, Math.fma(_t47, _t25, _t43), Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_s68ff79b2_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t37, double _t38, double _t48, double _t49) {
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / java.lang.Math.sqrt(_t60));
        double _t64 = t * Math.atan2(java.lang.Math.sqrt(_t60), _t38 * _t51);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_api_s68ff79b2_2(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_s68ff79b2_2(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _otherx, double _othery, double _t11_inv, double _t18, double _t19, double _t25, double _t27, double _t28, double _t38, double _t48, double _t49, double _t68, double _t69, double _t72, double _t73) {
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest.putDouble(destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.putDouble(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.putDouble(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.putDouble(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.abs(_selfx));
        dest.putDouble(destOffset + 8, java.lang.Math.abs(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.acos(_selfx));
        dest.putDouble(destOffset + 8, Math.acos(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(scalar, bX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        dest.putDouble(destOffset, Math.fma(scalar, _bx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.fma(bX, cX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        dest.putDouble(destOffset, Math.fma(_bx, _cx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _ct0 = java.lang.Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Double2OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _ct0 = java.lang.Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double2OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.asin(_selfx));
        dest.putDouble(destOffset + 8, Math.asin(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.atan(_selfx));
        dest.putDouble(destOffset + 8, Math.atan(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.atan2(_selfx, x));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.atan2(_selfx, xX));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, xY));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _xx = x.getDouble(xOffset);
        double _xy = x.getDouble(xOffset + 8);
        dest.putDouble(destOffset, Math.atan2(_selfx, _xx));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.cbrt(_selfx));
        dest.putDouble(destOffset + 8, Math.cbrt(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, min), max));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double maxX, double maxY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double maxX, double maxY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, minX), maxX));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _minx = min.getDouble(minOffset);
        double _miny = min.getDouble(minOffset + 8);
        double _maxx = max.getDouble(maxOffset);
        double _maxy = max.getDouble(maxOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, _minx), _maxx));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static double compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset) + src.getDouble(srcOffset + 8);
    }

    public static double compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.max(src.getDouble(srcOffset), src.getDouble(srcOffset + 8));
    }

    public static double compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.min(src.getDouble(srcOffset), src.getDouble(srcOffset + 8));
    }

    public static double compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset) * src.getDouble(srcOffset + 8);
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.copySign(_selfx, sign));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.copySign(_selfx, signX));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, signY));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _signx = sign.getDouble(signOffset);
        double _signy = sign.getDouble(signOffset + 8);
        dest.putDouble(destOffset, Math.copySign(_selfx, _signx));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.cos(_selfx));
        dest.putDouble(destOffset + 8, Math.cos(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.cosh(_selfx));
        dest.putDouble(destOffset + 8, Math.cosh(_selfy));
        return dest;
    }

    public static double cross_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.cross_unsafe(_srcBase, otherX, otherY);
    }

    public static double cross_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        return Math.fma(otherY, src.getDouble(srcOffset), -(otherX * src.getDouble(srcOffset + 8)));
    }

    public static double cross_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.cross_unsafe(_srcBase, _otherBase);
    }

    public static double cross_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getDouble(otherOffset + 8), src.getDouble(srcOffset), -(other.getDouble(otherOffset) * src.getDouble(srcOffset + 8)));
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.toDegrees(_selfx));
        dest.putDouble(destOffset + 8, Math.toDegrees(_selfy));
        return dest;
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _t0 = src.getDouble(srcOffset) - otherX;
        double _t1 = src.getDouble(srcOffset + 8) - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _t0 = src.getDouble(srcOffset) - other.getDouble(otherOffset);
        double _t1 = src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8);
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _t0 = src.getDouble(srcOffset) - otherX;
        double _t1 = src.getDouble(srcOffset + 8) - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _t0 = src.getDouble(srcOffset) - other.getDouble(otherOffset);
        double _t1 = src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8);
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        return Math.fma(otherX, src.getDouble(srcOffset), otherY * src.getDouble(srcOffset + 8));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getDouble(otherOffset), src.getDouble(srcOffset), other.getDouble(otherOffset + 8) * src.getDouble(srcOffset + 8));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.exp(_selfx));
        dest.putDouble(destOffset + 8, Math.exp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.pow(2.0, _selfx));
        dest.putDouble(destOffset + 8, Math.pow(2.0, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.expm1(_selfx));
        dest.putDouble(destOffset + 8, Math.expm1(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double NrefX, double NrefY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double NrefX, double NrefY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t2 = Math.fma(IX, NrefX, IY * NrefY) < 0.0 ? 1.0 : -1.0;
        dest.putDouble(destOffset, _selfx * _t2);
        dest.putDouble(destOffset + 8, _selfy * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Double2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t2 = Math.fma(I.getDouble(IOffset), Nref.getDouble(NrefOffset), I.getDouble(IOffset + 8) * Nref.getDouble(NrefOffset + 8)) < 0.0 ? 1.0 : -1.0;
        dest.putDouble(destOffset, _selfx * _t2);
        dest.putDouble(destOffset + 8, _selfy * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.floor(_selfx));
        dest.putDouble(destOffset + 8, Math.floor(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.hypot(_selfx, y));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.hypot(_selfx, yX));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, yY));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _yx = y.getDouble(yOffset);
        double _yy = y.getDouble(yOffset + 8);
        dest.putDouble(destOffset, Math.hypot(_selfx, _yx));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, 1.0 / _selfx);
        dest.putDouble(destOffset + 8, 1.0 / _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, (1.0 / java.lang.Math.sqrt(_selfx)));
        dest.putDouble(destOffset + 8, (1.0 / java.lang.Math.sqrt(_selfy)));
        return dest;
    }

    public static double length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        return java.lang.Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static double lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.log(_selfx));
        dest.putDouble(destOffset + 8, Math.log(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.log10(_selfx));
        dest.putDouble(destOffset + 8, Math.log10(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.log1p(_selfx));
        dest.putDouble(destOffset + 8, Math.log1p(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.log2(_selfx));
        dest.putDouble(destOffset + 8, Math.log2(_selfy));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        return java.lang.Math.abs(src.getDouble(srcOffset) - otherX) + java.lang.Math.abs(src.getDouble(srcOffset + 8) - otherY);
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.getDouble(srcOffset) - other.getDouble(otherOffset)) + java.lang.Math.abs(src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8));
    }

    public static double manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.getDouble(srcOffset)) + java.lang.Math.abs(src.getDouble(srcOffset + 8));
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, scalar));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, otherX));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, otherY));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, _otherx));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, _othery));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, scalar));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, otherX));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, otherY));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, _otherx));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, _othery));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, flooredMod(_selfx, y));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, flooredMod(_selfx, yX));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, yY));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _yx = y.getDouble(yOffset);
        double _yy = y.getDouble(yOffset + 8);
        dest.putDouble(destOffset, flooredMod(_selfx, _yx));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, _yy));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.nextDown(_selfx));
        dest.putDouble(destOffset + 8, Math.nextDown(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.nextUp(_selfx));
        dest.putDouble(destOffset + 8, Math.nextUp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t2 = (1.0 / java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t2);
            dest.putDouble(destOffset + 8, _selfy * _t2);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t3 = length * (1.0 / java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t3);
            dest.putDouble(destOffset + 8, _selfy * _t3);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY);
    }

    public static double orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Double2OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static double orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static double orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase);
    }

    public static double orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double2OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, rowX * _selfx);
        dest.putDouble(destOffset + 8, rowX * _selfy);
        dest.putDouble(destOffset + 16, rowY * _selfx);
        dest.putDouble(destOffset + 24, rowY * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _rowx = row.getDouble(rowOffset);
        double _rowy = row.getDouble(rowOffset + 8);
        dest.putDouble(destOffset, _rowx * _selfx);
        dest.putDouble(destOffset + 8, _rowx * _selfy);
        dest.putDouble(destOffset + 16, _rowy * _selfx);
        dest.putDouble(destOffset + 24, _rowy * _selfy);
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.pow(_selfx, exponent));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.pow(_selfx, exponentX));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _exponentx = exponent.getDouble(exponentOffset);
        double _exponenty = exponent.getDouble(exponentOffset + 8);
        dest.putDouble(destOffset, Math.pow(_selfx, _exponentx));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY) {
        double _t5 = Math.fma(ontoX, src.getDouble(srcOffset), ontoY * src.getDouble(srcOffset + 8)) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dest.putDouble(destOffset, ontoX * _t5);
        dest.putDouble(destOffset + 8, ontoY * _t5);
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Double2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        double _ontox = onto.getDouble(ontoOffset);
        double _ontoy = onto.getDouble(ontoOffset + 8);
        double _t5 = Math.fma(_ontox, src.getDouble(srcOffset), _ontoy * src.getDouble(srcOffset + 8)) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        dest.putDouble(destOffset, _ontox * _t5);
        dest.putDouble(destOffset + 8, _ontoy * _t5);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        dest.putDouble(destOffset, Math.fma(-normalX, _t1, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.putDouble(destOffset, Math.fma(-_normalx, _t1, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.toRadians(_selfx));
        dest.putDouble(destOffset + 8, Math.toRadians(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t2 = 2.0 * Math.fma(normalX, _selfx, normalY * _selfy);
        dest.putDouble(destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _t2 = 2.0 * Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.putDouble(destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double eta) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            dest.putDouble(destOffset, Math.fma(eta, _selfx, -(normalX * _t9)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            dest.putDouble(destOffset, Math.fma(eta, _selfx, -(_normalx * _t9)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.rint(_selfx));
        dest.putDouble(destOffset + 8, Math.rint(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.signum(_selfx));
        dest.putDouble(destOffset + 8, Math.signum(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.sin(_selfx));
        dest.putDouble(destOffset + 8, Math.sin(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.sinh(_selfx));
        dest.putDouble(destOffset + 8, Math.sinh(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t7 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - edge0) * _t0_inv));
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - edge0) * _t0_inv));
        dest.putDouble(destOffset, Math.fma(-2.0, _t7, 3.0) * _t7 * _t7);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - edge0Y) / (edge1Y - edge0Y)));
        dest.putDouble(destOffset, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        double _edge0x = edge0.getDouble(edge0Offset);
        double _edge0y = edge0.getDouble(edge0Offset + 8);
        double _t8 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - _edge0x) / (edge1.getDouble(edge1Offset) - _edge0x)));
        double _t9 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - _edge0y) / (edge1.getDouble(edge1Offset + 8) - _edge0y)));
        dest.putDouble(destOffset, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, java.lang.Math.sqrt(_selfx));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx < edgeX ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edgeY ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _edgex = edge.getDouble(edgeOffset);
        double _edgey = edge.getDouble(edgeOffset + 8);
        dest.putDouble(destOffset, _selfx < _edgex ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < _edgey ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.tan(_selfx));
        dest.putDouble(destOffset + 8, Math.tan(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.tanh(_selfx));
        dest.putDouble(destOffset + 8, Math.tanh(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        dest.putDouble(destOffset, Math.ulp(_selfx));
        dest.putDouble(destOffset + 8, Math.ulp(_selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 16);
        double _mat11 = mat.getDouble(matOffset + 24);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMulDirectionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 16);
        double _mat11 = mat.getDouble(matOffset + 24);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMulDirectionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 24);
        double _mat11 = mat.getDouble(matOffset + 32);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat03 = mat.getDouble(matOffset + 96);
        double _mat13 = mat.getDouble(matOffset + 104);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat03)));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMulPositionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 16);
        double _mat11 = mat.getDouble(matOffset + 24);
        double _mat02 = mat.getDouble(matOffset + 32);
        double _mat12 = mat.getDouble(matOffset + 40);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double2OpsKernelsAddress.preMulPositionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat01 = mat.getDouble(matOffset + 24);
        double _mat11 = mat.getDouble(matOffset + 32);
        double _mat02 = mat.getDouble(matOffset + 48);
        double _mat12 = mat.getDouble(matOffset + 56);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - pivotX;
        double _t3 = src.getDouble(srcOffset + 8) - pivotY;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - _pivotx;
        double _t3 = src.getDouble(srcOffset + 8) - _pivoty;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
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
