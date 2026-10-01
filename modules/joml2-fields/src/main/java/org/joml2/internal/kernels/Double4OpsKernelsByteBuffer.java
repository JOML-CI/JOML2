// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double4Ops} and its sibling kernel units. Not public API.
 */
public final class Double4OpsKernelsByteBuffer {
    private Double4OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, otherX + _selfx);
        dest.putDouble(destOffset + 8, otherY + _selfy);
        dest.putDouble(destOffset + 16, otherZ + _selfz);
        dest.putDouble(destOffset + 24, otherW + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _otherx + _selfx);
        dest.putDouble(destOffset + 8, _othery + _selfy);
        dest.putDouble(destOffset + 16, _otherz + _selfz);
        dest.putDouble(destOffset + 24, _otherw + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx / scalar);
        dest.putDouble(destOffset + 8, _selfy / scalar);
        dest.putDouble(destOffset + 16, _selfz / scalar);
        dest.putDouble(destOffset + 24, _selfw / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx / otherX);
        dest.putDouble(destOffset + 8, _selfy / otherY);
        dest.putDouble(destOffset + 16, _selfz / otherZ);
        dest.putDouble(destOffset + 24, _selfw / otherW);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _selfx / _otherx);
        dest.putDouble(destOffset + 8, _selfy / _othery);
        dest.putDouble(destOffset + 16, _selfz / _otherz);
        dest.putDouble(destOffset + 24, _selfw / _otherw);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY, double cZ, double cW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, b, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, cY));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, b, cZ));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _cx = c.getDouble(cOffset + 0);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        double _cw = c.getDouble(cOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, b, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, b, _cy));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, b, _cz));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, bX, cX));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, bY, cY));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, bZ, cZ));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _bx = b.getDouble(bOffset + 0);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        double _bw = b.getDouble(bOffset + 24);
        double _cx = c.getDouble(cOffset + 0);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        double _cw = c.getDouble(cOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _bx, _cx));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _by, _cy));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _bz, _cz));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, scalar * _selfx);
        dest.putDouble(destOffset + 8, scalar * _selfy);
        dest.putDouble(destOffset + 16, scalar * _selfz);
        dest.putDouble(destOffset + 24, scalar * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, otherX * _selfx);
        dest.putDouble(destOffset + 8, otherY * _selfy);
        dest.putDouble(destOffset + 16, otherZ * _selfz);
        dest.putDouble(destOffset + 24, otherW * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _otherx * _selfx);
        dest.putDouble(destOffset + 8, _othery * _selfy);
        dest.putDouble(destOffset + 16, _otherz * _selfz);
        dest.putDouble(destOffset + 24, _otherw * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, -_selfx);
        dest.putDouble(destOffset + 8, -_selfy);
        dest.putDouble(destOffset + 16, -_selfz);
        dest.putDouble(destOffset + 24, -_selfw);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx - otherX);
        dest.putDouble(destOffset + 8, _selfy - otherY);
        dest.putDouble(destOffset + 16, _selfz - otherZ);
        dest.putDouble(destOffset + 24, _selfw - otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _selfx - _otherx);
        dest.putDouble(destOffset + 8, _selfy - _othery);
        dest.putDouble(destOffset + 16, _selfz - _otherz);
        dest.putDouble(destOffset + 24, _selfw - _otherw);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, double u, double v, double w) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, double u, double v, double w) {
        double _t0 = Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        dest.putDouble(destOffset + 0, Math.cosFromSin(_t4, _t1) * _t5);
        dest.putDouble(destOffset + 8, _t4 * _t5);
        dest.putDouble(destOffset + 16, Math.cosFromSin(_t6, _t3) * _t0);
        dest.putDouble(destOffset + 24, _t6 * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        dest.putDouble(destOffset + 0, vX);
        dest.putDouble(destOffset + 8, vY);
        dest.putDouble(destOffset + 16, vZ);
        dest.putDouble(destOffset + 24, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _vw = v.getDouble(vOffset + 24);
        dest.putDouble(destOffset + 0, _vx);
        dest.putDouble(destOffset + 8, _vy);
        dest.putDouble(destOffset + 16, _vz);
        dest.putDouble(destOffset + 24, _vw);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset + 0, s);
        dest.putDouble(destOffset + 8, s);
        dest.putDouble(destOffset + 16, s);
        dest.putDouble(destOffset + 24, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset + 0, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset + 0, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.putDouble(destOffset + 8, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.putDouble(destOffset + 16, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        dest.putDouble(destOffset + 24, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _p3x = p3.getDouble(p3Offset + 0);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _p3w = p3.getDouble(p3Offset + 24);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.putDouble(destOffset + 0, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.putDouble(destOffset + 8, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.putDouble(destOffset + 16, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        dest.putDouble(destOffset + 24, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset + 0, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.putDouble(destOffset + 16, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.putDouble(destOffset + 24, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.putDouble(destOffset + 0, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.putDouble(destOffset + 16, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.putDouble(destOffset + 24, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset + 0, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.putDouble(destOffset + 16, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.putDouble(destOffset + 24, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.putDouble(destOffset + 0, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.putDouble(destOffset + 24, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset + 0, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.putDouble(destOffset + 24, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _p3x = p3.getDouble(p3Offset + 0);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _p3w = p3.getDouble(p3Offset + 24);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.putDouble(destOffset + 0, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.putDouble(destOffset + 24, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset + 0, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        dest.putDouble(destOffset + 16, 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        dest.putDouble(destOffset + 24, 0.5 * (Math.fma(2.0, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _p3x = p3.getDouble(p3Offset + 0);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _p3w = p3.getDouble(p3Offset + 24);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.putDouble(destOffset + 0, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.putDouble(destOffset + 8, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.putDouble(destOffset + 16, 0.5 * (Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), _t0, Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        dest.putDouble(destOffset + 24, 0.5 * (Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), _t0, Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = t * t;
        dest.putDouble(destOffset + 0, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.putDouble(destOffset + 16, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.putDouble(destOffset + 24, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _p1x = p1.getDouble(p1Offset + 0);
        double _p1y = p1.getDouble(p1Offset + 8);
        double _p1z = p1.getDouble(p1Offset + 16);
        double _p1w = p1.getDouble(p1Offset + 24);
        double _p2x = p2.getDouble(p2Offset + 0);
        double _p2y = p2.getDouble(p2Offset + 8);
        double _p2z = p2.getDouble(p2Offset + 16);
        double _p2w = p2.getDouble(p2Offset + 24);
        double _p3x = p3.getDouble(p3Offset + 0);
        double _p3y = p3.getDouble(p3Offset + 8);
        double _p3z = p3.getDouble(p3Offset + 16);
        double _p3w = p3.getDouble(p3Offset + 24);
        double _t0 = t * t;
        dest.putDouble(destOffset + 0, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.putDouble(destOffset + 8, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.putDouble(destOffset + 16, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        dest.putDouble(destOffset + 24, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), Math.fma(3.0 * Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0x = t0.getDouble(t0Offset + 0);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _t0z = t0.getDouble(t0Offset + 16);
        double _t0w = t0.getDouble(t0Offset + 24);
        double _v1x = v1.getDouble(v1Offset + 0);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _v1z = v1.getDouble(v1Offset + 16);
        double _v1w = v1.getDouble(v1Offset + 24);
        double _t1x = t1.getDouble(t1Offset + 0);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t1z = t1.getDouble(t1Offset + 16);
        double _t1w = t1.getDouble(t1Offset + 24);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0x = t0.getDouble(t0Offset + 0);
        double _t0y = t0.getDouble(t0Offset + 8);
        double _t0z = t0.getDouble(t0Offset + 16);
        double _t0w = t0.getDouble(t0Offset + 24);
        double _v1x = v1.getDouble(v1Offset + 0);
        double _v1y = v1.getDouble(v1Offset + 8);
        double _v1z = v1.getDouble(v1Offset + 16);
        double _v1w = v1.getDouble(v1Offset + 24);
        double _t1x = t1.getDouble(t1Offset + 0);
        double _t1y = t1.getDouble(t1Offset + 8);
        double _t1z = t1.getDouble(t1Offset + 16);
        double _t1w = t1.getDouble(t1Offset + 24);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, otherZ - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, _otherz - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(tX, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(tY, otherY - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _tx = t.getDouble(tOffset + 0);
        double _ty = t.getDouble(tOffset + 8);
        double _tz = t.getDouble(tOffset + 16);
        double _tw = t.getDouble(tOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / Math.sqrt(_t9));
        double _t14 = (1.0 / Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t28 = t * Math.sqrt(_t10) + (1.0 - t) * Math.sqrt(_t9);
        double _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
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
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dest.putDouble(destOffset + 0, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.putDouble(destOffset + 8, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.putDouble(destOffset + 16, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.putDouble(destOffset + 24, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Double4OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t7 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / Math.sqrt(_t36));
        double _t41 = (1.0 / Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * Math.sqrt(_t37) * (_t25 / _t8);
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
        double _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / Math.sqrt(_t106));
        double _t110 = t * Math.atan2(Math.sqrt(_t106), _t63 * _t90);
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
                    dest.putDouble(destOffset + 0, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.putDouble(destOffset + 24, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.putDouble(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.putDouble(destOffset + 24, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.putDouble(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.putDouble(destOffset + 16, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.putDouble(destOffset + 24, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.putDouble(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
            dest.putDouble(destOffset + 16, Math.fma(t, otherZ - _selfz, _selfz));
            dest.putDouble(destOffset + 24, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t11 = (1.0 / Math.sqrt(_t9));
        double _t14 = (1.0 / Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t28 = t * Math.sqrt(_t10) + (1.0 - t) * Math.sqrt(_t9);
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
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t61 = t * Math.atan2(Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dest.putDouble(destOffset + 0, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.putDouble(destOffset + 8, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.putDouble(destOffset + 16, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.putDouble(destOffset + 24, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double4OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t7 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / Math.sqrt(_t36));
        double _t41 = (1.0 / Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * Math.sqrt(_t37) * (_t25 / _t8);
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
        double _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / Math.sqrt(_t106));
        double _t110 = t * Math.atan2(Math.sqrt(_t106), _t63 * _t90);
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
                    dest.putDouble(destOffset + 0, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.putDouble(destOffset + 24, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.putDouble(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.putDouble(destOffset + 8, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.putDouble(destOffset + 16, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.putDouble(destOffset + 24, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.putDouble(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.putDouble(destOffset + 8, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.putDouble(destOffset + 16, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.putDouble(destOffset + 24, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.putDouble(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
            dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
            dest.putDouble(destOffset + 16, Math.fma(t, _otherz - _selfz, _selfz));
            dest.putDouble(destOffset + 24, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.abs(_selfx));
        dest.putDouble(destOffset + 8, Math.abs(_selfy));
        dest.putDouble(destOffset + 16, Math.abs(_selfz));
        dest.putDouble(destOffset + 24, Math.abs(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.acos(_selfx));
        dest.putDouble(destOffset + 8, Math.acos(_selfy));
        dest.putDouble(destOffset + 16, Math.acos(_selfz));
        dest.putDouble(destOffset + 24, Math.acos(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(scalar, bX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, bY, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(scalar, bZ, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _bx = b.getDouble(bOffset + 0);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        double _bw = b.getDouble(bOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(scalar, _bx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(scalar, _by, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(scalar, _bz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(bX, cX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(bY, cY, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(bZ, cZ, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _bx = b.getDouble(bOffset + 0);
        double _by = b.getDouble(bOffset + 8);
        double _bz = b.getDouble(bOffset + 16);
        double _bw = b.getDouble(bOffset + 24);
        double _cx = c.getDouble(cOffset + 0);
        double _cy = c.getDouble(cOffset + 8);
        double _cz = c.getDouble(cOffset + 16);
        double _cw = c.getDouble(cOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_bx, _cx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(_by, _cy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(_bz, _cz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        double _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        double _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        double _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2(Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Double4OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t6 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
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
        double _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static double angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        double _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        double _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        double _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static double angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double4OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t6 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        double _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        double _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        double _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        double _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        double _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        double _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.asin(_selfx));
        dest.putDouble(destOffset + 8, Math.asin(_selfy));
        dest.putDouble(destOffset + 16, Math.asin(_selfz));
        dest.putDouble(destOffset + 24, Math.asin(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.atan(_selfx));
        dest.putDouble(destOffset + 8, Math.atan(_selfy));
        dest.putDouble(destOffset + 16, Math.atan(_selfz));
        dest.putDouble(destOffset + 24, Math.atan(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.atan2(_selfx, x));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, x));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, x));
        dest.putDouble(destOffset + 24, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY, double xZ, double xW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY, double xZ, double xW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.atan2(_selfx, xX));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, xY));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, xZ));
        dest.putDouble(destOffset + 24, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _xx = x.getDouble(xOffset + 0);
        double _xy = x.getDouble(xOffset + 8);
        double _xz = x.getDouble(xOffset + 16);
        double _xw = x.getDouble(xOffset + 24);
        dest.putDouble(destOffset + 0, Math.atan2(_selfx, _xx));
        dest.putDouble(destOffset + 8, Math.atan2(_selfy, _xy));
        dest.putDouble(destOffset + 16, Math.atan2(_selfz, _xz));
        dest.putDouble(destOffset + 24, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.cbrt(_selfx));
        dest.putDouble(destOffset + 8, Math.cbrt(_selfy));
        dest.putDouble(destOffset + 16, Math.cbrt(_selfz));
        dest.putDouble(destOffset + 24, Math.cbrt(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, Math.ceil(_selfy));
        dest.putDouble(destOffset + 16, Math.ceil(_selfz));
        dest.putDouble(destOffset + 24, Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(Math.max(_selfx, min), max));
        dest.putDouble(destOffset + 8, Math.min(Math.max(_selfy, min), max));
        dest.putDouble(destOffset + 16, Math.min(Math.max(_selfz, min), max));
        dest.putDouble(destOffset + 24, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(Math.max(_selfx, minX), maxX));
        dest.putDouble(destOffset + 8, Math.min(Math.max(_selfy, minY), maxY));
        dest.putDouble(destOffset + 16, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.putDouble(destOffset + 24, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _minx = min.getDouble(minOffset + 0);
        double _miny = min.getDouble(minOffset + 8);
        double _minz = min.getDouble(minOffset + 16);
        double _minw = min.getDouble(minOffset + 24);
        double _maxx = max.getDouble(maxOffset + 0);
        double _maxy = max.getDouble(maxOffset + 8);
        double _maxz = max.getDouble(maxOffset + 16);
        double _maxw = max.getDouble(maxOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.putDouble(destOffset + 8, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.putDouble(destOffset + 16, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.putDouble(destOffset + 24, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return _selfw + (_selfz + (_selfx + _selfy));
    }

    public static double compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.max(Math.max(Math.max(_selfx, _selfy), _selfz), _selfw);
    }

    public static double compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.min(Math.min(Math.min(_selfx, _selfy), _selfz), _selfw);
    }

    public static double compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return _selfw * _selfz * _selfx * _selfy;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.copySign(_selfx, sign));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, sign));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, sign));
        dest.putDouble(destOffset + 24, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY, double signZ, double signW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY, double signZ, double signW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.copySign(_selfx, signX));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, signY));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, signZ));
        dest.putDouble(destOffset + 24, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _signx = sign.getDouble(signOffset + 0);
        double _signy = sign.getDouble(signOffset + 8);
        double _signz = sign.getDouble(signOffset + 16);
        double _signw = sign.getDouble(signOffset + 24);
        dest.putDouble(destOffset + 0, Math.copySign(_selfx, _signx));
        dest.putDouble(destOffset + 8, Math.copySign(_selfy, _signy));
        dest.putDouble(destOffset + 16, Math.copySign(_selfz, _signz));
        dest.putDouble(destOffset + 24, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.cos(_selfx));
        dest.putDouble(destOffset + 8, Math.cos(_selfy));
        dest.putDouble(destOffset + 16, Math.cos(_selfz));
        dest.putDouble(destOffset + 24, Math.cos(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.cosh(_selfx));
        dest.putDouble(destOffset + 8, Math.cosh(_selfy));
        dest.putDouble(destOffset + 16, Math.cosh(_selfz));
        dest.putDouble(destOffset + 24, Math.cosh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        dest.putDouble(destOffset + 0, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.putDouble(destOffset + 8, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.putDouble(destOffset + 16, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.putDouble(destOffset + 24, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        long _wBase = UnsafeOpsHolder.U.getLong(w, UnsafeCopy.BB_ADDRESS_OFFSET) + wOffset;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _vw = v.getDouble(vOffset + 24);
        double _wx = w.getDouble(wOffset + 0);
        double _wy = w.getDouble(wOffset + 8);
        double _wz = w.getDouble(wOffset + 16);
        double _ww = w.getDouble(wOffset + 24);
        double _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        double _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        double _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        double _t15 = Math.fma(_vx, _wz, -(_vz * _wx));
        double _t16 = Math.fma(_vx, _ww, -(_vw * _wx));
        double _t17 = Math.fma(_vx, _wy, -(_vy * _wx));
        dest.putDouble(destOffset + 0, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.putDouble(destOffset + 8, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.putDouble(destOffset + 16, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.putDouble(destOffset + 24, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.toDegrees(_selfx));
        dest.putDouble(destOffset + 8, Math.toDegrees(_selfy));
        dest.putDouble(destOffset + 16, Math.toDegrees(_selfz));
        dest.putDouble(destOffset + 24, Math.toDegrees(_selfw));
        return dest;
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfw - otherW;
        double _t1 = _selfz - otherZ;
        double _t2 = _selfx - otherX;
        double _t3 = _selfy - otherY;
        return Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t0 = _selfw - _otherw;
        double _t1 = _selfz - _otherz;
        double _t2 = _selfx - _otherx;
        double _t3 = _selfy - _othery;
        return Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfw - otherW;
        double _t1 = _selfz - otherZ;
        double _t2 = _selfx - otherX;
        double _t3 = _selfy - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t0 = _selfw - _otherw;
        double _t1 = _selfz - _otherz;
        double _t2 = _selfx - _otherx;
        double _t3 = _selfy - _othery;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        return Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.exp(_selfx));
        dest.putDouble(destOffset + 8, Math.exp(_selfy));
        dest.putDouble(destOffset + 16, Math.exp(_selfz));
        dest.putDouble(destOffset + 24, Math.exp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.pow(2.0, _selfx));
        dest.putDouble(destOffset + 8, Math.pow(2.0, _selfy));
        dest.putDouble(destOffset + 16, Math.pow(2.0, _selfz));
        dest.putDouble(destOffset + 24, Math.pow(2.0, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.expm1(_selfx));
        dest.putDouble(destOffset + 8, Math.expm1(_selfy));
        dest.putDouble(destOffset + 16, Math.expm1(_selfz));
        dest.putDouble(destOffset + 24, Math.expm1(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        if (Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0) {
            dest.putDouble(destOffset + 0, _selfx);
            dest.putDouble(destOffset + 8, _selfy);
            dest.putDouble(destOffset + 16, _selfz);
            dest.putDouble(destOffset + 24, _selfw);
        } else {
            dest.putDouble(destOffset + 0, -_selfx);
            dest.putDouble(destOffset + 8, -_selfy);
            dest.putDouble(destOffset + 16, -_selfz);
            dest.putDouble(destOffset + 24, -_selfw);
        }
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _Ix = I.getDouble(IOffset + 0);
        double _Iy = I.getDouble(IOffset + 8);
        double _Iz = I.getDouble(IOffset + 16);
        double _Iw = I.getDouble(IOffset + 24);
        double _Nrefx = Nref.getDouble(NrefOffset + 0);
        double _Nrefy = Nref.getDouble(NrefOffset + 8);
        double _Nrefz = Nref.getDouble(NrefOffset + 16);
        double _Nrefw = Nref.getDouble(NrefOffset + 24);
        if (Math.fma(_Iw, _Nrefw, Math.fma(_Iz, _Nrefz, Math.fma(_Ix, _Nrefx, _Iy * _Nrefy))) < 0.0) {
            dest.putDouble(destOffset + 0, _selfx);
            dest.putDouble(destOffset + 8, _selfy);
            dest.putDouble(destOffset + 16, _selfz);
            dest.putDouble(destOffset + 24, _selfw);
        } else {
            dest.putDouble(destOffset + 0, -_selfx);
            dest.putDouble(destOffset + 8, -_selfy);
            dest.putDouble(destOffset + 16, -_selfz);
            dest.putDouble(destOffset + 24, -_selfw);
        }
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.floor(_selfx));
        dest.putDouble(destOffset + 8, Math.floor(_selfy));
        dest.putDouble(destOffset + 16, Math.floor(_selfz));
        dest.putDouble(destOffset + 24, Math.floor(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.putDouble(destOffset + 8, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.putDouble(destOffset + 16, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        dest.putDouble(destOffset + 24, Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.hypot(_selfx, y));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, y));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, y));
        dest.putDouble(destOffset + 24, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.hypot(_selfx, yX));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, yY));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, yZ));
        dest.putDouble(destOffset + 24, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _yx = y.getDouble(yOffset + 0);
        double _yy = y.getDouble(yOffset + 8);
        double _yz = y.getDouble(yOffset + 16);
        double _yw = y.getDouble(yOffset + 24);
        dest.putDouble(destOffset + 0, Math.hypot(_selfx, _yx));
        dest.putDouble(destOffset + 8, Math.hypot(_selfy, _yy));
        dest.putDouble(destOffset + 16, Math.hypot(_selfz, _yz));
        dest.putDouble(destOffset + 24, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, 1.0 / _selfx);
        dest.putDouble(destOffset + 8, 1.0 / _selfy);
        dest.putDouble(destOffset + 16, 1.0 / _selfz);
        dest.putDouble(destOffset + 24, 1.0 / _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, (1.0 / Math.sqrt(_selfx)));
        dest.putDouble(destOffset + 8, (1.0 / Math.sqrt(_selfy)));
        dest.putDouble(destOffset + 16, (1.0 / Math.sqrt(_selfz)));
        dest.putDouble(destOffset + 24, (1.0 / Math.sqrt(_selfw)));
        return dest;
    }

    public static double length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static double lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.log(_selfx));
        dest.putDouble(destOffset + 8, Math.log(_selfy));
        dest.putDouble(destOffset + 16, Math.log(_selfz));
        dest.putDouble(destOffset + 24, Math.log(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.log10(_selfx));
        dest.putDouble(destOffset + 8, Math.log10(_selfy));
        dest.putDouble(destOffset + 16, Math.log10(_selfz));
        dest.putDouble(destOffset + 24, Math.log10(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.log1p(_selfx));
        dest.putDouble(destOffset + 8, Math.log1p(_selfy));
        dest.putDouble(destOffset + 16, Math.log1p(_selfz));
        dest.putDouble(destOffset + 24, Math.log1p(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.log2(_selfx));
        dest.putDouble(destOffset + 8, Math.log2(_selfy));
        dest.putDouble(destOffset + 16, Math.log2(_selfz));
        dest.putDouble(destOffset + 24, Math.log2(_selfw));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY) + Math.abs(_selfz - otherZ) + Math.abs(_selfw - otherW);
    }

    public static double manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery) + Math.abs(_selfz - _otherz) + Math.abs(_selfw - _otherw);
    }

    public static double manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.abs(_selfx) + Math.abs(_selfy) + Math.abs(_selfz) + Math.abs(_selfw);
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.max(_selfx, scalar));
        dest.putDouble(destOffset + 8, Math.max(_selfy, scalar));
        dest.putDouble(destOffset + 16, Math.max(_selfz, scalar));
        dest.putDouble(destOffset + 24, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.max(_selfx, otherX));
        dest.putDouble(destOffset + 8, Math.max(_selfy, otherY));
        dest.putDouble(destOffset + 16, Math.max(_selfz, otherZ));
        dest.putDouble(destOffset + 24, Math.max(_selfw, otherW));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.max(_selfx, _otherx));
        dest.putDouble(destOffset + 8, Math.max(_selfy, _othery));
        dest.putDouble(destOffset + 16, Math.max(_selfz, _otherz));
        dest.putDouble(destOffset + 24, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(_selfx, scalar));
        dest.putDouble(destOffset + 8, Math.min(_selfy, scalar));
        dest.putDouble(destOffset + 16, Math.min(_selfz, scalar));
        dest.putDouble(destOffset + 24, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(_selfx, otherX));
        dest.putDouble(destOffset + 8, Math.min(_selfy, otherY));
        dest.putDouble(destOffset + 16, Math.min(_selfz, otherZ));
        dest.putDouble(destOffset + 24, Math.min(_selfw, otherW));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset + 0);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.min(_selfx, _otherx));
        dest.putDouble(destOffset + 8, Math.min(_selfy, _othery));
        dest.putDouble(destOffset + 16, Math.min(_selfz, _otherz));
        dest.putDouble(destOffset + 24, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, flooredMod(_selfx, y));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, y));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, y));
        dest.putDouble(destOffset + 24, flooredMod(_selfw, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, flooredMod(_selfx, yX));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, yY));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, yZ));
        dest.putDouble(destOffset + 24, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _yx = y.getDouble(yOffset + 0);
        double _yy = y.getDouble(yOffset + 8);
        double _yz = y.getDouble(yOffset + 16);
        double _yw = y.getDouble(yOffset + 24);
        dest.putDouble(destOffset + 0, flooredMod(_selfx, _yx));
        dest.putDouble(destOffset + 8, flooredMod(_selfy, _yy));
        dest.putDouble(destOffset + 16, flooredMod(_selfz, _yz));
        dest.putDouble(destOffset + 24, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.nextDown(_selfx));
        dest.putDouble(destOffset + 8, Math.nextDown(_selfy));
        dest.putDouble(destOffset + 16, Math.nextDown(_selfz));
        dest.putDouble(destOffset + 24, Math.nextDown(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.nextUp(_selfx));
        dest.putDouble(destOffset + 8, Math.nextUp(_selfy));
        dest.putDouble(destOffset + 16, Math.nextUp(_selfz));
        dest.putDouble(destOffset + 24, Math.nextUp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.putDouble(destOffset + 0, _selfx * _t4);
            dest.putDouble(destOffset + 8, _selfy * _t4);
            dest.putDouble(destOffset + 16, _selfz * _t4);
            dest.putDouble(destOffset + 24, _selfw * _t4);
        } else {
            dest.putDouble(destOffset + 0, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t5 = length * (1.0 / Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.putDouble(destOffset + 0, _selfx * _t5);
            dest.putDouble(destOffset + 8, _selfy * _t5);
            dest.putDouble(destOffset + 16, _selfz * _t5);
            dest.putDouble(destOffset + 24, _selfw * _t5);
        } else {
            dest.putDouble(destOffset + 0, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, rowX * _selfx);
        dest.putDouble(destOffset + 8, rowX * _selfy);
        dest.putDouble(destOffset + 16, rowX * _selfz);
        dest.putDouble(destOffset + 24, rowX * _selfw);
        dest.putDouble(destOffset + 32, rowY * _selfx);
        dest.putDouble(destOffset + 40, rowY * _selfy);
        dest.putDouble(destOffset + 48, rowY * _selfz);
        dest.putDouble(destOffset + 56, rowY * _selfw);
        dest.putDouble(destOffset + 64, rowZ * _selfx);
        dest.putDouble(destOffset + 72, rowZ * _selfy);
        dest.putDouble(destOffset + 80, rowZ * _selfz);
        dest.putDouble(destOffset + 88, rowZ * _selfw);
        dest.putDouble(destOffset + 96, rowW * _selfx);
        dest.putDouble(destOffset + 104, rowW * _selfy);
        dest.putDouble(destOffset + 112, rowW * _selfz);
        dest.putDouble(destOffset + 120, rowW * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _rowx = row.getDouble(rowOffset + 0);
        double _rowy = row.getDouble(rowOffset + 8);
        double _rowz = row.getDouble(rowOffset + 16);
        double _roww = row.getDouble(rowOffset + 24);
        dest.putDouble(destOffset + 0, _rowx * _selfx);
        dest.putDouble(destOffset + 8, _rowx * _selfy);
        dest.putDouble(destOffset + 16, _rowx * _selfz);
        dest.putDouble(destOffset + 24, _rowx * _selfw);
        dest.putDouble(destOffset + 32, _rowy * _selfx);
        dest.putDouble(destOffset + 40, _rowy * _selfy);
        dest.putDouble(destOffset + 48, _rowy * _selfz);
        dest.putDouble(destOffset + 56, _rowy * _selfw);
        dest.putDouble(destOffset + 64, _rowz * _selfx);
        dest.putDouble(destOffset + 72, _rowz * _selfy);
        dest.putDouble(destOffset + 80, _rowz * _selfz);
        dest.putDouble(destOffset + 88, _rowz * _selfw);
        dest.putDouble(destOffset + 96, _roww * _selfx);
        dest.putDouble(destOffset + 104, _roww * _selfy);
        dest.putDouble(destOffset + 112, _roww * _selfz);
        dest.putDouble(destOffset + 120, _roww * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.pow(_selfx, exponent));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponent));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, exponent));
        dest.putDouble(destOffset + 24, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.pow(_selfx, exponentX));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, exponentY));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, exponentZ));
        dest.putDouble(destOffset + 24, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _exponentx = exponent.getDouble(exponentOffset + 0);
        double _exponenty = exponent.getDouble(exponentOffset + 8);
        double _exponentz = exponent.getDouble(exponentOffset + 16);
        double _exponentw = exponent.getDouble(exponentOffset + 24);
        dest.putDouble(destOffset + 0, Math.pow(_selfx, _exponentx));
        dest.putDouble(destOffset + 8, Math.pow(_selfy, _exponenty));
        dest.putDouble(destOffset + 16, Math.pow(_selfz, _exponentz));
        dest.putDouble(destOffset + 24, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = Math.fma(ontoW, _selfw, Math.fma(ontoZ, _selfz, Math.fma(ontoX, _selfx, ontoY * _selfy))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.putDouble(destOffset + 0, ontoX * _t9);
        dest.putDouble(destOffset + 8, ontoY * _t9);
        dest.putDouble(destOffset + 16, ontoZ * _t9);
        dest.putDouble(destOffset + 24, ontoW * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _ontox = onto.getDouble(ontoOffset + 0);
        double _ontoy = onto.getDouble(ontoOffset + 8);
        double _ontoz = onto.getDouble(ontoOffset + 16);
        double _ontow = onto.getDouble(ontoOffset + 24);
        double _t9 = Math.fma(_ontow, _selfw, Math.fma(_ontoz, _selfz, Math.fma(_ontox, _selfx, _ontoy * _selfy))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.putDouble(destOffset + 0, _ontox * _t9);
        dest.putDouble(destOffset + 8, _ontoy * _t9);
        dest.putDouble(destOffset + 16, _ontoz * _t9);
        dest.putDouble(destOffset + 24, _ontow * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.putDouble(destOffset + 0, Math.fma(-normalX, _t3, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t3, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-normalZ, _t3, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _normalx = normal.getDouble(normalOffset + 0);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _normalw = normal.getDouble(normalOffset + 24);
        double _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.putDouble(destOffset + 0, Math.fma(-_normalx, _t3, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t3, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-_normalz, _t3, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.toRadians(_selfx));
        dest.putDouble(destOffset + 8, Math.toRadians(_selfy));
        dest.putDouble(destOffset + 16, Math.toRadians(_selfz));
        dest.putDouble(destOffset + 24, Math.toRadians(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = 2.0 * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.putDouble(destOffset + 0, Math.fma(-normalX, _t4, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-normalY, _t4, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-normalZ, _t4, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _normalx = normal.getDouble(normalOffset + 0);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _normalw = normal.getDouble(normalOffset + 24);
        double _t4 = 2.0 * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.putDouble(destOffset + 0, Math.fma(-_normalx, _t4, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(-_normaly, _t4, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(-_normalz, _t4, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.putDouble(destOffset + 0, Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.putDouble(destOffset + 16, Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.putDouble(destOffset + 24, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.putDouble(destOffset + 0, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _normalx = normal.getDouble(normalOffset + 0);
        double _normaly = normal.getDouble(normalOffset + 8);
        double _normalz = normal.getDouble(normalOffset + 16);
        double _normalw = normal.getDouble(normalOffset + 24);
        double _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.putDouble(destOffset + 0, Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.putDouble(destOffset + 8, Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.putDouble(destOffset + 16, Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.putDouble(destOffset + 24, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.putDouble(destOffset + 0, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.rint(_selfx));
        dest.putDouble(destOffset + 8, Math.rint(_selfy));
        dest.putDouble(destOffset + 16, Math.rint(_selfz));
        dest.putDouble(destOffset + 24, Math.rint(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.signum(_selfx));
        dest.putDouble(destOffset + 8, Math.signum(_selfy));
        dest.putDouble(destOffset + 16, Math.signum(_selfz));
        dest.putDouble(destOffset + 24, Math.signum(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.sin(_selfx));
        dest.putDouble(destOffset + 8, Math.sin(_selfy));
        dest.putDouble(destOffset + 16, Math.sin(_selfz));
        dest.putDouble(destOffset + 24, Math.sin(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.sinh(_selfx));
        dest.putDouble(destOffset + 8, Math.sinh(_selfy));
        dest.putDouble(destOffset + 16, Math.sinh(_selfz));
        dest.putDouble(destOffset + 24, Math.sinh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = Math.max(0.0, Math.min(1.0, (_selfx - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (_selfy - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (_selfz - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (_selfw - edge0) * _t0_inv));
        dest.putDouble(destOffset + 0, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        dest.putDouble(destOffset + 24, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t16 = Math.max(0.0, Math.min(1.0, (_selfx - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (_selfz - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (_selfw - edge0W) / (edge1W - edge0W)));
        dest.putDouble(destOffset + 0, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.putDouble(destOffset + 24, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _edge0x = edge0.getDouble(edge0Offset + 0);
        double _edge0y = edge0.getDouble(edge0Offset + 8);
        double _edge0z = edge0.getDouble(edge0Offset + 16);
        double _edge0w = edge0.getDouble(edge0Offset + 24);
        double _edge1x = edge1.getDouble(edge1Offset + 0);
        double _edge1y = edge1.getDouble(edge1Offset + 8);
        double _edge1z = edge1.getDouble(edge1Offset + 16);
        double _edge1w = edge1.getDouble(edge1Offset + 24);
        double _t16 = Math.max(0.0, Math.min(1.0, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        double _t17 = Math.max(0.0, Math.min(1.0, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (_selfz - _edge0z) / (_edge1z - _edge0z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (_selfw - _edge0w) / (_edge1w - _edge0w)));
        dest.putDouble(destOffset + 0, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.putDouble(destOffset + 8, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.putDouble(destOffset + 16, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.putDouble(destOffset + 24, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.sqrt(_selfx));
        dest.putDouble(destOffset + 8, Math.sqrt(_selfy));
        dest.putDouble(destOffset + 16, Math.sqrt(_selfz));
        dest.putDouble(destOffset + 24, Math.sqrt(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < edge ? 0.0 : 1.0);
        dest.putDouble(destOffset + 24, _selfw < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx < edgeX ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < edgeY ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < edgeZ ? 0.0 : 1.0);
        dest.putDouble(destOffset + 24, _selfw < edgeW ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _edgex = edge.getDouble(edgeOffset + 0);
        double _edgey = edge.getDouble(edgeOffset + 8);
        double _edgez = edge.getDouble(edgeOffset + 16);
        double _edgew = edge.getDouble(edgeOffset + 24);
        dest.putDouble(destOffset + 0, _selfx < _edgex ? 0.0 : 1.0);
        dest.putDouble(destOffset + 8, _selfy < _edgey ? 0.0 : 1.0);
        dest.putDouble(destOffset + 16, _selfz < _edgez ? 0.0 : 1.0);
        dest.putDouble(destOffset + 24, _selfw < _edgew ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.tan(_selfx));
        dest.putDouble(destOffset + 8, Math.tan(_selfy));
        dest.putDouble(destOffset + 16, Math.tan(_selfz));
        dest.putDouble(destOffset + 24, Math.tan(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.tanh(_selfx));
        dest.putDouble(destOffset + 8, Math.tanh(_selfy));
        dest.putDouble(destOffset + 16, Math.tanh(_selfz));
        dest.putDouble(destOffset + 24, Math.tanh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.putDouble(destOffset + 8, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.putDouble(destOffset + 16, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.putDouble(destOffset + 24, _selfw >= 0.0 ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.ulp(_selfx));
        dest.putDouble(destOffset + 8, Math.ulp(_selfy));
        dest.putDouble(destOffset + 16, Math.ulp(_selfz));
        dest.putDouble(destOffset + 24, Math.ulp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Double4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _mat00 = mat.getDouble(matOffset + 0);
        double _mat10 = mat.getDouble(matOffset + 8);
        double _mat20 = mat.getDouble(matOffset + 16);
        double _mat30 = mat.getDouble(matOffset + 24);
        double _mat01 = mat.getDouble(matOffset + 32);
        double _mat11 = mat.getDouble(matOffset + 40);
        double _mat21 = mat.getDouble(matOffset + 48);
        double _mat31 = mat.getDouble(matOffset + 56);
        double _mat02 = mat.getDouble(matOffset + 64);
        double _mat12 = mat.getDouble(matOffset + 72);
        double _mat22 = mat.getDouble(matOffset + 80);
        double _mat32 = mat.getDouble(matOffset + 88);
        double _mat03 = mat.getDouble(matOffset + 96);
        double _mat13 = mat.getDouble(matOffset + 104);
        double _mat23 = mat.getDouble(matOffset + 112);
        double _mat33 = mat.getDouble(matOffset + 120);
        dest.putDouble(destOffset + 0, Math.fma(_mat03, _selfw, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        dest.putDouble(destOffset + 8, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.putDouble(destOffset + 24, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.putDouble(destOffset + 0, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _quatx = quat.getDouble(quatOffset + 0);
        double _quaty = quat.getDouble(quatOffset + 8);
        double _quatz = quat.getDouble(quatOffset + 16);
        double _quatw = quat.getDouble(quatOffset + 24);
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.putDouble(destOffset + 0, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.putDouble(destOffset + 0, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _axisx = axis.getDouble(axisOffset + 0);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.putDouble(destOffset + 0, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.putDouble(destOffset + 0, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _quatx = quat.getDouble(quatOffset + 0);
        double _quaty = quat.getDouble(quatOffset + 8);
        double _quatz = quat.getDouble(quatOffset + 16);
        double _quatw = quat.getDouble(quatOffset + 24);
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.putDouble(destOffset + 0, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putDouble(destOffset + 8, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.putDouble(destOffset + 16, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, _selfx);
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t0, _selfz * _t1));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset + 0);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.putDouble(destOffset + 16, _selfz);
        dest.putDouble(destOffset + 24, _selfw);
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
