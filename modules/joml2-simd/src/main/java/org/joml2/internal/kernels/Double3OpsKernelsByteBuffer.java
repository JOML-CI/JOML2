// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3Ops} and its sibling kernel units. Not public API.
 */
public final class Double3OpsKernelsByteBuffer {
    private Double3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, otherX + _selfx);
        dest.putDouble(destOffset + 8, otherY + _selfy);
        dest.putDouble(destOffset + 16, otherZ + _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, _otherx + _selfx);
        dest.putDouble(destOffset + 8, _othery + _selfy);
        dest.putDouble(destOffset + 16, _otherz + _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx / scalar);
        dest.putDouble(destOffset + 8, _selfy / scalar);
        dest.putDouble(destOffset + 16, _selfz / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx / otherX);
        dest.putDouble(destOffset + 8, _selfy / otherY);
        dest.putDouble(destOffset + 16, _selfz / otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, _selfx / _otherx);
        dest.putDouble(destOffset + 8, _selfy / _othery);
        dest.putDouble(destOffset + 16, _selfz / _otherz);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(_selfx, b, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, cY));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, b, cZ));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        dest.putDouble(destOffset, Math.fma(_selfx, b, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, _cy));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, b, _cz));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(_selfx, bX, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, bY, cY));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, bZ, cZ));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        dest.putDouble(destOffset, Math.fma(_selfx, _bx, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _by, _cy));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, scalar * _selfx);
        dest.putDouble(destOffset + 8, scalar * _selfy);
        dest.putDouble(destOffset + 16, scalar * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, otherX * _selfx);
        dest.putDouble(destOffset + 8, otherY * _selfy);
        dest.putDouble(destOffset + 16, otherZ * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, _otherx * _selfx);
        dest.putDouble(destOffset + 8, _othery * _selfy);
        dest.putDouble(destOffset + 16, _otherz * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, -_selfx);
        dest.putDouble(destOffset + 8, -_selfy);
        dest.putDouble(destOffset + 16, -_selfz);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx - otherX);
        dest.putDouble(destOffset + 8, _selfy - otherY);
        dest.putDouble(destOffset + 16, _selfz - otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, _selfx - _otherx);
        dest.putDouble(destOffset + 8, _selfy - _othery);
        dest.putDouble(destOffset + 16, _selfz - _otherz);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, double u, double v) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, double u, double v) {
        double _t1 = v * 6.283185307179586;
        double _t2 = Math.sin(_t1);
        double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
        dest.putDouble(destOffset, _t5 * Math.cosFromSin(_t2, _t1));
        dest.putDouble(destOffset + 8, _t5 * _t2);
        dest.putDouble(destOffset + 16, Math.fma(2.0, u, -1.0));
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, vY);
        dest.putDouble(destOffset + 16, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, _vy);
        dest.putDouble(destOffset + 16, _vz);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, s);
        dest.putDouble(destOffset + 16, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.putDouble(destOffset + 8, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.putDouble(destOffset + 16, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.putDouble(destOffset + 8, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.putDouble(destOffset + 16, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.putDouble(destOffset + 16, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.putDouble(destOffset + 16, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.putDouble(destOffset + 16, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        dest.putDouble(destOffset + 16, 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_api_sada6039f_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_sada6039f_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0, double _t1) {
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.putDouble(destOffset + 16, 0.5 * (Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), _t0, Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = t * t;
        dest.putDouble(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.putDouble(destOffset + 16, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p3x = p3.getDouble(p3Offset);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _t0 = t * t;
        dest.putDouble(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_api_s3901f8d6_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_s3901f8d6_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0) {
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.putDouble(destOffset + 16, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0x = t0.getDouble(t0Offset);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _t0z = t0.getDouble(t0Offset + 16);
        double _v1x = v1.getDouble(v1Offset);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _v1z = v1.getDouble(v1Offset + 16);
        double _t1x = t1.getDouble(t1Offset);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t1z = t1.getDouble(t1Offset + 16);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0x = t0.getDouble(t0Offset);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _t0z = t0.getDouble(t0Offset + 16);
        double _v1x = v1.getDouble(v1Offset);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _v1z = v1.getDouble(v1Offset + 16);
        double _t1x = t1.getDouble(t1Offset);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t1z = t1.getDouble(t1Offset + 16);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(tY, otherY - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(tZ, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        double _tz = t.getDouble(tOffset + 16);
        dest.putDouble(destOffset, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_s2ea49ec7_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, Math.fma(otherZ, _t12, -(_t26 * _t17)), Math.fma(otherX, _t12, -(_t26 * _t14)), Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_s2ea49ec7_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = Math.fma(_t39, _t17, _t33);
        double _t41 = Math.fma(_t39, _t14, _t34);
        double _t42 = Math.fma(_t39, _t20, _t35);
        double _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        dest.putDouble(destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.putDouble(destOffset + 8, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.putDouble(destOffset + 16, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = Math.fma(_t73, _t31, _t61);
        double _t75 = Math.fma(_t73, _t33, _t62);
        double _t76 = Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t99 = _t48 * Math.sin(_t95);
        double _t100 = _t48 * Math.cos(_t95);
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.putDouble(destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.putDouble(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.putDouble(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.putDouble(destOffset + 16, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.putDouble(destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
            dest.putDouble(destOffset + 16, Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_api_seb057073_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, Math.fma(_otherz, _t12, -(_t26 * _t17)), Math.fma(_otherx, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_seb057073_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = Math.fma(_t39, _t17, _t33);
        double _t41 = Math.fma(_t39, _t14, _t34);
        double _t42 = Math.fma(_t39, _t20, _t35);
        double _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        dest.putDouble(destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.putDouble(destOffset + 8, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.putDouble(destOffset + 16, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = java.lang.Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = Math.fma(_t73, _t31, _t61);
        double _t75 = Math.fma(_t73, _t33, _t62);
        double _t76 = Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t99 = _t48 * Math.sin(_t95);
        double _t100 = _t48 * Math.cos(_t95);
        double _t104, _t105, _t106;
        if (_t91 > 0.0) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.putDouble(destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.putDouble(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.putDouble(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.putDouble(destOffset + 16, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.putDouble(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
            dest.putDouble(destOffset + 16, Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.abs(_selfx));
        dest.putDouble(destOffset + 8, java.lang.Math.abs(_selfy));
        dest.putDouble(destOffset + 16, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.acos(_selfx));
        dest.putDouble(destOffset + 8, Math.acos(_selfy));
        dest.putDouble(destOffset + 16, Math.acos(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(scalar, bX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, bY, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(scalar, bZ, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        dest.putDouble(destOffset, Math.fma(scalar, _bx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, _by, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(bX, cX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(bY, cY, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(bZ, cZ, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _bx = b.getDouble(bOffset);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        double _cx = c.getDouble(cOffset);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        dest.putDouble(destOffset, Math.fma(_bx, _cx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_by, _cy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        double _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        double _t23 = unitScale(_t21, _t22, _t20);
        double _t27 = _t20 * _t23;
        double _t28 = _t21 * _t23;
        double _t29 = _t22 * _t23;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.asin(_selfx));
        dest.putDouble(destOffset + 8, Math.asin(_selfy));
        dest.putDouble(destOffset + 16, Math.asin(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.atan(_selfx));
        dest.putDouble(destOffset + 8, Math.atan(_selfy));
        dest.putDouble(destOffset + 16, Math.atan(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.atan2(_selfx, x));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, x));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY, double xZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY, double xZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.atan2(_selfx, xX));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, xY));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _xx = x.getDouble(xOffset);
        double _xy = x.getDouble(xOffset + 8);
        double _xz = x.getDouble(xOffset + 16);
        dest.putDouble(destOffset, Math.atan2(_selfx, _xx));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, _xy));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.cbrt(_selfx));
        dest.putDouble(destOffset + 8, Math.cbrt(_selfy));
        dest.putDouble(destOffset + 16, Math.cbrt(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, Math.ceil(_selfy));
        dest.putDouble(destOffset + 16, Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, min), max));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.putDouble(destOffset + 16, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, minX), maxX));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.putDouble(destOffset + 16, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _minx = min.getDouble(minOffset);
        double _miny = min.getDouble(minOffset + 8);
        double _minz = min.getDouble(minOffset + 16);
        double _maxx = max.getDouble(maxOffset);
        double _maxy = max.getDouble(maxOffset + 8);
        double _maxz = max.getDouble(maxOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, _minx), _maxx));
        dest.putDouble(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.putDouble(destOffset + 16, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, src.getDouble(srcOffset + 16) - lineStartZ, Math.fma(_t1, src.getDouble(srcOffset) - lineStartX, _t2 * (src.getDouble(srcOffset + 8) - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, Math.fma(_t1, _t14, lineStartX));
            dest.putDouble(destOffset + 8, Math.fma(_t2, _t14, lineStartY));
            dest.putDouble(destOffset + 16, Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.putDouble(destOffset, lineStartX);
            dest.putDouble(destOffset + 8, lineStartY);
            dest.putDouble(destOffset + 16, lineStartZ);
        }
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _lineStartBase = UnsafeOpsHolder.U.getLong(lineStart, UnsafeCopy.BB_ADDRESS_OFFSET) + lineStartOffset;
        long _lineEndBase = UnsafeOpsHolder.U.getLong(lineEnd, UnsafeCopy.BB_ADDRESS_OFFSET) + lineEndOffset;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        double _lineStartx = lineStart.getDouble(lineStartOffset);
        double _lineStarty = lineStart.getDouble(lineStartOffset + 8);
        double _lineStartz = lineStart.getDouble(lineStartOffset + 16);
        double _t0 = lineEnd.getDouble(lineEndOffset + 16) - _lineStartz;
        double _t1 = lineEnd.getDouble(lineEndOffset) - _lineStartx;
        double _t2 = lineEnd.getDouble(lineEndOffset + 8) - _lineStarty;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, src.getDouble(srcOffset + 16) - _lineStartz, Math.fma(_t1, src.getDouble(srcOffset) - _lineStartx, _t2 * (src.getDouble(srcOffset + 8) - _lineStarty))) / _t10));
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, Math.fma(_t1, _t14, _lineStartx));
            dest.putDouble(destOffset + 8, Math.fma(_t2, _t14, _lineStarty));
            dest.putDouble(destOffset + 16, Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.putDouble(destOffset, _lineStartx);
            dest.putDouble(destOffset + 8, _lineStarty);
            dest.putDouble(destOffset + 16, _lineStartz);
        }
        return dest;
    }

    public static double compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset + 16) + (src.getDouble(srcOffset) + src.getDouble(srcOffset + 8));
    }

    public static double compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(src.getDouble(srcOffset), src.getDouble(srcOffset + 8)), src.getDouble(srcOffset + 16));
    }

    public static double compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(src.getDouble(srcOffset), src.getDouble(srcOffset + 8)), src.getDouble(srcOffset + 16));
    }

    public static double compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getDouble(srcOffset + 16) * src.getDouble(srcOffset) * src.getDouble(srcOffset + 8);
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.copySign(_selfx, sign));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, sign));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY, double signZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY, double signZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.copySign(_selfx, signX));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, signY));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _signx = sign.getDouble(signOffset);
        double _signy = sign.getDouble(signOffset + 8);
        double _signz = sign.getDouble(signOffset + 16);
        dest.putDouble(destOffset, Math.copySign(_selfx, _signx));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, _signy));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.cos(_selfx));
        dest.putDouble(destOffset + 8, Math.cos(_selfy));
        dest.putDouble(destOffset + 16, Math.cos(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.cosh(_selfx));
        dest.putDouble(destOffset + 8, Math.cosh(_selfy));
        dest.putDouble(destOffset + 16, Math.cosh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putDouble(destOffset + 8, Math.fma(otherX, _selfz, -(otherZ * _selfx)));
        dest.putDouble(destOffset + 16, Math.fma(otherY, _selfx, -(otherX * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putDouble(destOffset + 8, Math.fma(_otherx, _selfz, -(_otherz * _selfx)));
        dest.putDouble(destOffset + 16, Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.toDegrees(_selfx));
        dest.putDouble(destOffset + 8, Math.toDegrees(_selfy));
        dest.putDouble(destOffset + 16, Math.toDegrees(_selfz));
        return dest;
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _t0 = src.getDouble(srcOffset + 16) - otherZ;
        double _t1 = src.getDouble(srcOffset) - otherX;
        double _t2 = src.getDouble(srcOffset + 8) - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _t0 = src.getDouble(srcOffset + 16) - other.getDouble(otherOffset + 16);
        double _t1 = src.getDouble(srcOffset) - other.getDouble(otherOffset);
        double _t2 = src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8);
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _t0 = src.getDouble(srcOffset + 16) - otherZ;
        double _t1 = src.getDouble(srcOffset) - otherX;
        double _t2 = src.getDouble(srcOffset + 8) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _t0 = src.getDouble(srcOffset + 16) - other.getDouble(otherOffset + 16);
        double _t1 = src.getDouble(srcOffset) - other.getDouble(otherOffset);
        double _t2 = src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        return Math.fma(otherZ, src.getDouble(srcOffset + 16), Math.fma(otherX, src.getDouble(srcOffset), otherY * src.getDouble(srcOffset + 8)));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getDouble(otherOffset + 16), src.getDouble(srcOffset + 16), Math.fma(other.getDouble(otherOffset), src.getDouble(srcOffset), other.getDouble(otherOffset + 8) * src.getDouble(srcOffset + 8)));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.exp(_selfx));
        dest.putDouble(destOffset + 8, Math.exp(_selfy));
        dest.putDouble(destOffset + 16, Math.exp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.pow(2.0, _selfx));
        dest.putDouble(destOffset + 8, Math.pow(2.0, _selfy));
        dest.putDouble(destOffset + 16, Math.pow(2.0, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.expm1(_selfx));
        dest.putDouble(destOffset + 8, Math.expm1(_selfy));
        dest.putDouble(destOffset + 16, Math.expm1(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t3 = Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
        dest.putDouble(destOffset, _selfx * _t3);
        dest.putDouble(destOffset + 8, _selfy * _t3);
        dest.putDouble(destOffset + 16, _selfz * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t3 = Math.fma(I.getDouble(IOffset + 16), Nref.getDouble(NrefOffset + 16), Math.fma(I.getDouble(IOffset), Nref.getDouble(NrefOffset), I.getDouble(IOffset + 8) * Nref.getDouble(NrefOffset + 8))) < 0.0 ? 1.0 : -1.0;
        dest.putDouble(destOffset, _selfx * _t3);
        dest.putDouble(destOffset + 8, _selfy * _t3);
        dest.putDouble(destOffset + 16, _selfz * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.floor(_selfx));
        dest.putDouble(destOffset + 8, Math.floor(_selfy));
        dest.putDouble(destOffset + 16, Math.floor(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.putDouble(destOffset + 16, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.hypot(_selfx, y));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, y));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.hypot(_selfx, yX));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, yY));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _yx = y.getDouble(yOffset);
        double _yy = y.getDouble(yOffset + 8);
        double _yz = y.getDouble(yOffset + 16);
        dest.putDouble(destOffset, Math.hypot(_selfx, _yx));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, _yy));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, 1.0 / _selfx);
        dest.putDouble(destOffset + 8, 1.0 / _selfy);
        dest.putDouble(destOffset + 16, 1.0 / _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, (1.0 / java.lang.Math.sqrt(_selfx)));
        dest.putDouble(destOffset + 8, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.putDouble(destOffset + 16, (1.0 / java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static double length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        return java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static double lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.log(_selfx));
        dest.putDouble(destOffset + 8, Math.log(_selfy));
        dest.putDouble(destOffset + 16, Math.log(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.log10(_selfx));
        dest.putDouble(destOffset + 8, Math.log10(_selfy));
        dest.putDouble(destOffset + 16, Math.log10(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.log1p(_selfx));
        dest.putDouble(destOffset + 8, Math.log1p(_selfy));
        dest.putDouble(destOffset + 16, Math.log1p(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.log2(_selfx));
        dest.putDouble(destOffset + 8, Math.log2(_selfy));
        dest.putDouble(destOffset + 16, Math.log2(_selfz));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(src.getDouble(srcOffset) - otherX) + java.lang.Math.abs(src.getDouble(srcOffset + 8) - otherY) + java.lang.Math.abs(src.getDouble(srcOffset + 16) - otherZ);
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.getDouble(srcOffset) - other.getDouble(otherOffset)) + java.lang.Math.abs(src.getDouble(srcOffset + 8) - other.getDouble(otherOffset + 8)) + java.lang.Math.abs(src.getDouble(srcOffset + 16) - other.getDouble(otherOffset + 16));
    }

    public static double manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.getDouble(srcOffset)) + java.lang.Math.abs(src.getDouble(srcOffset + 8)) + java.lang.Math.abs(src.getDouble(srcOffset + 16));
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, scalar));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, scalar));
        dest.putDouble(destOffset + 16, java.lang.Math.max(_selfz, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, otherX));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, otherY));
        dest.putDouble(destOffset + 16, java.lang.Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.max(_selfx, _otherx));
        dest.putDouble(destOffset + 8, java.lang.Math.max(_selfy, _othery));
        dest.putDouble(destOffset + 16, java.lang.Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, scalar));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, scalar));
        dest.putDouble(destOffset + 16, java.lang.Math.min(_selfz, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, otherX));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, otherY));
        dest.putDouble(destOffset + 16, java.lang.Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.min(_selfx, _otherx));
        dest.putDouble(destOffset + 8, java.lang.Math.min(_selfy, _othery));
        dest.putDouble(destOffset + 16, java.lang.Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, flooredMod(_selfx, y));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, y));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, flooredMod(_selfx, yX));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, yY));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _yx = y.getDouble(yOffset);
        double _yy = y.getDouble(yOffset + 8);
        double _yz = y.getDouble(yOffset + 16);
        dest.putDouble(destOffset, flooredMod(_selfx, _yx));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, _yy));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.nextDown(_selfx));
        dest.putDouble(destOffset + 8, Math.nextDown(_selfy));
        dest.putDouble(destOffset + 16, Math.nextDown(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.nextUp(_selfx));
        dest.putDouble(destOffset + 8, Math.nextUp(_selfy));
        dest.putDouble(destOffset + 16, Math.nextUp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t3);
            dest.putDouble(destOffset + 8, _selfy * _t3);
            dest.putDouble(destOffset + 16, _selfz * _t3);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t4);
            dest.putDouble(destOffset + 8, _selfy * _t4);
            dest.putDouble(destOffset + 16, _selfz * _t4);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        double _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
    }

    public static double orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Double3OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        double _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        double _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        double _t31 = _t23 * _t27;
        double _t32 = _t24 * _t27;
        double _t33 = _t25 * _t27;
        double _t40 = Math.atan2(java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0 ? -_t40 : _t40;
    }

    public static double orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _t7 = unitScale(_normalx, _normaly, _normalz);
        double _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        double _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0 ? -_t18 : _t18;
    }

    public static double orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Double3OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        double _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        double _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        double _t27 = unitScale(_t24, _t25, _t23);
        return orientedAngle_degenerate_api_sb9f89de8_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t27, _t23 * _t27, _t24 * _t27, _t25 * _t27);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_api_sb9f89de8_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t27, double _t31, double _t32, double _t33) {
        double _t40 = Math.atan2(java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0 ? -_t40 : _t40;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, rowX * _selfx);
        dest.putDouble(destOffset + 8, rowX * _selfy);
        dest.putDouble(destOffset + 16, rowX * _selfz);
        dest.putDouble(destOffset + 24, rowY * _selfx);
        dest.putDouble(destOffset + 32, rowY * _selfy);
        dest.putDouble(destOffset + 40, rowY * _selfz);
        dest.putDouble(destOffset + 48, rowZ * _selfx);
        dest.putDouble(destOffset + 56, rowZ * _selfy);
        dest.putDouble(destOffset + 64, rowZ * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _rowx = row.getDouble(rowOffset);
        double _rowy = row.getDouble(rowOffset + 8);
        double _rowz = row.getDouble(rowOffset + 16);
        dest.putDouble(destOffset, _rowx * _selfx);
        dest.putDouble(destOffset + 8, _rowx * _selfy);
        dest.putDouble(destOffset + 16, _rowx * _selfz);
        dest.putDouble(destOffset + 24, _rowy * _selfx);
        dest.putDouble(destOffset + 32, _rowy * _selfy);
        dest.putDouble(destOffset + 40, _rowy * _selfz);
        dest.putDouble(destOffset + 48, _rowz * _selfx);
        dest.putDouble(destOffset + 56, _rowz * _selfy);
        dest.putDouble(destOffset + 64, _rowz * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer perpendicular_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer perpendicular_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.putDouble(destOffset, _selfy);
            dest.putDouble(destOffset + 8, -_selfx);
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, _selfz);
            dest.putDouble(destOffset + 16, -_selfy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.pow(_selfx, exponent));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponent));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.pow(_selfx, exponentX));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponentY));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _exponentx = exponent.getDouble(exponentOffset);
        double _exponenty = exponent.getDouble(exponentOffset + 8);
        double _exponentz = exponent.getDouble(exponentOffset + 16);
        dest.putDouble(destOffset, Math.pow(_selfx, _exponentx));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, _exponenty));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        double _t7 = Math.fma(ontoZ, src.getDouble(srcOffset + 16), Math.fma(ontoX, src.getDouble(srcOffset), ontoY * src.getDouble(srcOffset + 8))) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest.putDouble(destOffset, ontoX * _t7);
        dest.putDouble(destOffset + 8, ontoY * _t7);
        dest.putDouble(destOffset + 16, ontoZ * _t7);
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        double _ontox = onto.getDouble(ontoOffset);
        double _ontoy = onto.getDouble(ontoOffset + 8);
        double _ontoz = onto.getDouble(ontoOffset + 16);
        double _t7 = Math.fma(_ontoz, src.getDouble(srcOffset + 16), Math.fma(_ontox, src.getDouble(srcOffset), _ontoy * src.getDouble(srcOffset + 8))) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.putDouble(destOffset, _ontox * _t7);
        dest.putDouble(destOffset + 8, _ontoy * _t7);
        dest.putDouble(destOffset + 16, _ontoz * _t7);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.putDouble(destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t2, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-normalZ, _t2, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.putDouble(destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t2, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.toRadians(_selfx));
        dest.putDouble(destOffset + 8, Math.toRadians(_selfy));
        dest.putDouble(destOffset + 16, Math.toRadians(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t3 = 2.0 * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.putDouble(destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t3, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-normalZ, _t3, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _t3 = 2.0 * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.putDouble(destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t3, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.putDouble(destOffset, Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.putDouble(destOffset + 16, Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _normalx = normal.getDouble(normalOffset);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.putDouble(destOffset, Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.putDouble(destOffset + 16, Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.rint(_selfx));
        dest.putDouble(destOffset + 8, Math.rint(_selfy));
        dest.putDouble(destOffset + 16, Math.rint(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.signum(_selfx));
        dest.putDouble(destOffset + 8, Math.signum(_selfy));
        dest.putDouble(destOffset + 16, Math.signum(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.sin(_selfx));
        dest.putDouble(destOffset + 8, Math.sin(_selfy));
        dest.putDouble(destOffset + 16, Math.sin(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.sinh(_selfx));
        dest.putDouble(destOffset + 8, Math.sinh(_selfy));
        dest.putDouble(destOffset + 16, Math.sinh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - edge0) * _t0_inv));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - edge0) * _t0_inv));
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 16) - edge0) * _t0_inv));
        dest.putDouble(destOffset, Math.fma(-2.0, _t10, 3.0) * _t10 * _t10);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t11, 3.0) * _t11 * _t11);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 16) - edge0Z) / (edge1Z - edge0Z)));
        dest.putDouble(destOffset, Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        double _edge0x = edge0.getDouble(edge0Offset);
        double _edge0y = edge0.getDouble(edge0Offset + 8);
        double _edge0z = edge0.getDouble(edge0Offset + 16);
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset) - _edge0x) / (edge1.getDouble(edge1Offset) - _edge0x)));
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 8) - _edge0y) / (edge1.getDouble(edge1Offset + 8) - _edge0y)));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.getDouble(srcOffset + 16) - _edge0z) / (edge1.getDouble(edge1Offset + 16) - _edge0z)));
        dest.putDouble(destOffset, Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, java.lang.Math.sqrt(_selfx));
        dest.putDouble(destOffset + 8, java.lang.Math.sqrt(_selfy));
        dest.putDouble(destOffset + 16, java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx < edgeX ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edgeY ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < edgeZ ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _edgex = edge.getDouble(edgeOffset);
        double _edgey = edge.getDouble(edgeOffset + 8);
        double _edgez = edge.getDouble(edgeOffset + 16);
        dest.putDouble(destOffset, _selfx < _edgex ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < _edgey ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < _edgez ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.tan(_selfx));
        dest.putDouble(destOffset + 8, Math.tan(_selfy));
        dest.putDouble(destOffset + 16, Math.tan(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.tanh(_selfx));
        dest.putDouble(destOffset + 8, Math.tanh(_selfy));
        dest.putDouble(destOffset + 16, Math.tanh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, _t12 * _t19);
        dest.putDouble(destOffset + 8, _t14 * _t19);
        dest.putDouble(destOffset + 16, _t13 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Double3OpsKernelsByteBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
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
        return triangleNormal_degenerate_api_s36d5c705_1(dest, destOffset, Math.fma(_t52, _t53, -(_t54 * _t55)), Math.fma(_t54, _t56, -(_t57 * _t53)), Math.fma(_t57, _t55, -(_t52 * _t56)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer triangleNormal_degenerate_api_s36d5c705_1(java.nio.ByteBuffer dest, int destOffset, double _t64, double _t65, double _t66) {
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            dest.putDouble(destOffset, _t77 * _t72);
            dest.putDouble(destOffset + 8, _t77 * _t73);
            dest.putDouble(destOffset + 16, _t77 * _t71);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = p1.getDouble(p1Offset + 8) - _selfy;
        double _t1 = p2.getDouble(p2Offset + 16) - _selfz;
        double _t2 = p1.getDouble(p1Offset + 16) - _selfz;
        double _t3 = p2.getDouble(p2Offset + 8) - _selfy;
        double _t4 = p1.getDouble(p1Offset) - _selfx;
        double _t5 = p2.getDouble(p2Offset) - _selfx;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsByteBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.putDouble(destOffset, _t12 * _t19);
        dest.putDouble(destOffset + 8, _t14 * _t19);
        dest.putDouble(destOffset + 16, _t13 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Double3OpsKernelsByteBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _p1x = p1.getDouble(p1Offset);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p2x = p2.getDouble(p2Offset);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _t19 = java.lang.Math.min(1.0, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), java.lang.Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
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
        return triangleNormal_degenerate_api_s4d3d020e_1(dest, destOffset, _t40, _t42, _t43, _t44, _t45, _t38 * _t44, _t41 * _t45, _t39 * _t44);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer triangleNormal_degenerate_api_s4d3d020e_1(java.nio.ByteBuffer dest, int destOffset, double _t40, double _t42, double _t43, double _t44, double _t45, double _t52, double _t53, double _t54) {
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        double _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        double _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        double _t67 = unitScale(_t65, _t66, _t64);
        double _t71 = _t64 * _t67;
        double _t72 = _t65 * _t67;
        double _t73 = _t66 * _t67;
        double _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        double _t77 = (1.0 / java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0) {
            dest.putDouble(destOffset, _t77 * _t72);
            dest.putDouble(destOffset + 8, _t77 * _t73);
            dest.putDouble(destOffset + 16, _t77 * _t71);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.putDouble(destOffset + 16, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, Math.ulp(_selfx));
        dest.putDouble(destOffset + 8, Math.ulp(_selfy));
        dest.putDouble(destOffset + 16, Math.ulp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer xyz0_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer xyz0_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, _selfz);
        dest.putDouble(destOffset + 24, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer xyz1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer xyz1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, _selfz);
        dest.putDouble(destOffset + 24, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat20 = mat.getDouble(matOffset + 16);
        double _mat01 = mat.getDouble(matOffset + 24);
        double _mat11 = mat.getDouble(matOffset + 32);
        double _mat21 = mat.getDouble(matOffset + 40);
        double _mat02 = mat.getDouble(matOffset + 48);
        double _mat12 = mat.getDouble(matOffset + 56);
        double _mat22 = mat.getDouble(matOffset + 64);
        dest.putDouble(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putDouble(destOffset + 8, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putDouble(destOffset + 16, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat01 = mat.getDouble(matOffset + 8);
        double _mat02 = mat.getDouble(matOffset + 16);
        double _mat10 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat12 = mat.getDouble(matOffset + 48);
        double _mat20 = mat.getDouble(matOffset + 64);
        double _mat21 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        dest.putDouble(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putDouble(destOffset + 8, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putDouble(destOffset + 16, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat20 = mat.getDouble(matOffset + 16);
        double _mat01 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat21 = mat.getDouble(matOffset + 48);
        double _mat02 = mat.getDouble(matOffset + 64);
        double _mat12 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        dest.putDouble(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putDouble(destOffset + 8, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putDouble(destOffset + 16, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat01 = mat.getDouble(matOffset + 8);
        double _mat02 = mat.getDouble(matOffset + 16);
        double _mat03 = mat.getDouble(matOffset + 24);
        double _mat10 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat12 = mat.getDouble(matOffset + 48);
        double _mat13 = mat.getDouble(matOffset + 56);
        double _mat20 = mat.getDouble(matOffset + 64);
        double _mat21 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        double _mat23 = mat.getDouble(matOffset + 88);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.putDouble(destOffset + 16, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat20 = mat.getDouble(matOffset + 16);
        double _mat01 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat21 = mat.getDouble(matOffset + 48);
        double _mat02 = mat.getDouble(matOffset + 64);
        double _mat12 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        double _mat03 = mat.getDouble(matOffset + 96);
        double _mat13 = mat.getDouble(matOffset + 104);
        double _mat23 = mat.getDouble(matOffset + 112);
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.putDouble(destOffset + 16, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.ByteBuffer preMulProject_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulProject_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _mat00 = mat.getDouble(matOffset);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat20 = mat.getDouble(matOffset + 16);
        double _mat01 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat21 = mat.getDouble(matOffset + 48);
        double _mat02 = mat.getDouble(matOffset + 64);
        double _mat12 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        double _mat03 = mat.getDouble(matOffset + 96);
        double _mat13 = mat.getDouble(matOffset + 104);
        double _mat23 = mat.getDouble(matOffset + 112);
        double _t2_inv = 1.0 / Math.fma(mat.getDouble(matOffset + 24), _selfx, Math.fma(mat.getDouble(matOffset + 56), _selfy, Math.fma(mat.getDouble(matOffset + 88), _selfz, mat.getDouble(matOffset + 120))));
        dest.putDouble(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))) * _t2_inv);
        return preMulProject_api_s6cb270c_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23, _t2_inv);
    }

    /** Piece 2 of {@code preMulProject_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulProject_api_s6cb270c_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat20, double _mat11, double _mat21, double _mat12, double _mat22, double _mat13, double _mat23, double _t2_inv) {
        dest.putDouble(destOffset + 8, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.putDouble(destOffset + 16, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.putDouble(destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _quatx = quat.getDouble(quatOffset);
        double _quaty = quat.getDouble(quatOffset + 8);
        double _quatz = quat.getDouble(quatOffset + 16);
        double _quatw = quat.getDouble(quatOffset + 24);
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.putDouble(destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.putDouble(destOffset, Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _quatx = quat.getDouble(quatOffset);
        double _quaty = quat.getDouble(quatOffset + 8);
        double _quatz = quat.getDouble(quatOffset + 16);
        double _quatw = quat.getDouble(quatOffset + 24);
        double _t0 = _selfy - pivot.getDouble(pivotOffset + 8);
        double _t1 = _selfx - pivot.getDouble(pivotOffset);
        double _t2 = _selfz - pivot.getDouble(pivotOffset + 16);
        double _t12 = 2.0 * Math.fma(_quatx, _t0, -(_quaty * _t1));
        double _t13 = 2.0 * Math.fma(_quatz, _t1, -(_quatx * _t2));
        double _t14 = 2.0 * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.putDouble(destOffset, Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.putDouble(destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.putDouble(destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - pivotX;
        double _t3 = src.getDouble(srcOffset + 16) - pivotZ;
        double _t4 = src.getDouble(srcOffset + 8) - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))));
        dest.putDouble(destOffset + 8, Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))));
        dest.putDouble(destOffset + 16, Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - _pivotx;
        double _t3 = src.getDouble(srcOffset + 16) - _pivotz;
        double _t4 = src.getDouble(srcOffset + 8) - _pivoty;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx))));
        dest.putDouble(destOffset + 8, Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty))));
        dest.putDouble(destOffset + 16, Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.putDouble(destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _quatx = quat.getDouble(quatOffset);
        double _quaty = quat.getDouble(quatOffset + 8);
        double _quatz = quat.getDouble(quatOffset + 16);
        double _quatw = quat.getDouble(quatOffset + 24);
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.putDouble(destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t0, _selfz * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotY, double pivotZ) {
        double _selfx = src.getDouble(srcOffset);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset + 8) - pivotY;
        double _t3 = src.getDouble(srcOffset + 16) - pivotZ;
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)));
        dest.putDouble(destOffset + 16, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset + 8) - _pivoty;
        double _t3 = src.getDouble(srcOffset + 16) - _pivotz;
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty)));
        dest.putDouble(destOffset + 16, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotZ) {
        double _selfy = src.getDouble(srcOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - pivotX;
        double _t3 = src.getDouble(srcOffset + 16) - pivotZ;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)));
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _selfy = src.getDouble(srcOffset + 8);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivotz = pivot.getDouble(pivotOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - _pivotx;
        double _t3 = src.getDouble(srcOffset + 16) - _pivotz;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx)));
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.putDouble(destOffset + 16, _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _selfz = src.getDouble(srcOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - pivotX;
        double _t3 = src.getDouble(srcOffset + 8) - pivotY;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        dest.putDouble(destOffset + 16, _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _selfz = src.getDouble(srcOffset + 16);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src.getDouble(srcOffset) - _pivotx;
        double _t3 = src.getDouble(srcOffset + 8) - _pivoty;
        dest.putDouble(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.putDouble(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
        dest.putDouble(destOffset + 16, _selfz);
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
