// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link DoubleQuatOps} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code DoubleQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class DoubleQuatOpsKernelsByteBuffer {
    private DoubleQuatOpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            dest.putDouble(destOffset, -(_selfx * _t3_inv));
            dest.putDouble(destOffset + 8, -(_selfy * _t3_inv));
            dest.putDouble(destOffset + 16, -(_selfz * _t3_inv));
            dest.putDouble(destOffset + 24, _selfw * _t3_inv);
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            dest.putDouble(destOffset, -(_selfx * _t3_inv));
            dest.putDouble(destOffset + 8, -(_selfy * _t3_inv));
            dest.putDouble(destOffset + 16, -(_selfz * _t3_inv));
            dest.putDouble(destOffset + 24, _selfw * _t3_inv);
            return dest;
        }
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        double _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        double _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putDouble(destOffset, -(_t20 * _t27_inv));
        dest.putDouble(destOffset + 8, -(_t23 * _t27_inv));
        dest.putDouble(destOffset + 16, -(_t22 * _t27_inv));
        dest.putDouble(destOffset + 24, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        double _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        double _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        double _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.putDouble(destOffset, -(_t20 * _t27_inv));
        dest.putDouble(destOffset + 8, -(_t23 * _t27_inv));
        dest.putDouble(destOffset + 16, -(_t22 * _t27_inv));
        dest.putDouble(destOffset + 24, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        double _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        double _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putDouble(destOffset, -(_t20 * _t27_inv));
        dest.putDouble(destOffset + 8, -(_t23 * _t27_inv));
        dest.putDouble(destOffset + 16, -(_t22 * _t27_inv));
        dest.putDouble(destOffset + 24, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        double _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        double _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        double _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.putDouble(destOffset, -(_t20 * _t27_inv));
        dest.putDouble(destOffset + 8, -(_t23 * _t27_inv));
        dest.putDouble(destOffset + 16, -(_t22 * _t27_inv));
        dest.putDouble(destOffset + 24, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, otherX + _selfx);
        dest.putDouble(destOffset + 8, otherY + _selfy);
        dest.putDouble(destOffset + 16, otherZ + _selfz);
        dest.putDouble(destOffset + 24, otherW + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, _otherx + _selfx);
        dest.putDouble(destOffset + 8, _othery + _selfy);
        dest.putDouble(destOffset + 16, _otherz + _selfz);
        dest.putDouble(destOffset + 24, _otherw + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, scalar * _selfx);
        dest.putDouble(destOffset + 8, scalar * _selfy);
        dest.putDouble(destOffset + 16, scalar * _selfz);
        dest.putDouble(destOffset + 24, scalar * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -_selfx);
        dest.putDouble(destOffset + 8, -_selfy);
        dest.putDouble(destOffset + 16, -_selfz);
        dest.putDouble(destOffset + 24, -_selfw);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, _selfx - otherX);
        dest.putDouble(destOffset + 8, _selfy - otherY);
        dest.putDouble(destOffset + 16, _selfz - otherZ);
        dest.putDouble(destOffset + 24, _selfw - otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, _selfx - _otherx);
        dest.putDouble(destOffset + 8, _selfy - _othery);
        dest.putDouble(destOffset + 16, _selfz - _otherz);
        dest.putDouble(destOffset + 24, _selfw - _otherw);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, double u1, double u2, double u3) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeUniformRotation_unsafe(_destBase, u1, u2, u3);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformRotation_api(java.nio.ByteBuffer dest, int destOffset, double u1, double u2, double u3) {
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = java.lang.Math.sqrt(1.0 - u1);
        dest.putDouble(destOffset, Math.sinWithCos(_t1) * _t4);
        dest.putDouble(destOffset + 8, Math.cosWithSin(_t1) * _t4);
        dest.putDouble(destOffset + 16, Math.sinWithCos(_t3) * _t0);
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t3) * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, vY);
        dest.putDouble(destOffset + 16, vZ);
        dest.putDouble(destOffset + 24, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        DoubleQuatOpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _vw = v.getDouble(vOffset + 24);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, _vy);
        dest.putDouble(destOffset + 16, _vz);
        dest.putDouble(destOffset + 24, _vw);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        dest.putDouble(destOffset, dqRX);
        dest.putDouble(destOffset + 8, dqRY);
        dest.putDouble(destOffset + 16, dqRZ);
        dest.putDouble(destOffset + 24, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m20 = m.getDouble(mOffset + 16);
        double _m01 = m.getDouble(mOffset + 24);
        double _m11 = m.getDouble(mOffset + 32);
        double _m21 = m.getDouble(mOffset + 40);
        double _m02 = m.getDouble(mOffset + 48);
        double _m12 = m.getDouble(mOffset + 56);
        double _m22 = m.getDouble(mOffset + 64);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x3_api_s18f08509_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat3x3_api_s18f08509_1(java.nio.ByteBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t1);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t4);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putDouble(destOffset, _sp1 * _t4);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m01 = m.getDouble(mOffset + 8);
        double _m02 = m.getDouble(mOffset + 16);
        double _m10 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m12 = m.getDouble(mOffset + 48);
        double _m20 = m.getDouble(mOffset + 64);
        double _m21 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x4_api_s725968d6_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat3x4_api_s725968d6_1(java.nio.ByteBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t1);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t4);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putDouble(destOffset, _sp1 * _t4);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m20 = m.getDouble(mOffset + 16);
        double _m01 = m.getDouble(mOffset + 32);
        double _m11 = m.getDouble(mOffset + 40);
        double _m21 = m.getDouble(mOffset + 48);
        double _m02 = m.getDouble(mOffset + 64);
        double _m12 = m.getDouble(mOffset + 72);
        double _m22 = m.getDouble(mOffset + 80);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat4x4_api_sbc07e171_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat4x4_api_sbc07e171_1(java.nio.ByteBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t1);
            dest.putDouble(destOffset + 8, _sp0 * _t7);
            dest.putDouble(destOffset + 16, _sp0 * _t9);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.putDouble(destOffset + 8, _sp3 * _t4);
                dest.putDouble(destOffset + 16, _sp3 * _t6);
                dest.putDouble(destOffset + 24, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putDouble(destOffset, _sp1 * _t4);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.putDouble(destOffset + 16, _sp1 * _t8);
                    dest.putDouble(destOffset + 24, _sp1 * _t7);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t6);
                    dest.putDouble(destOffset + 8, _sp2 * _t8);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.putDouble(destOffset + 24, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, _selfz);
        dest.putDouble(destOffset + 24, _selfw);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 72, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 80, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return toMatrix_api_fma_s6c371ec5_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_fma_s6c371ec5_1(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset + 88, 0.0);
        dest.putDouble(destOffset + 96, 0.0);
        dest.putDouble(destOffset + 104, 0.0);
        dest.putDouble(destOffset + 112, 0.0);
        dest.putDouble(destOffset + 120, 1.0);
        return dest;
    }

    /** {@code toMatrix_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        dest.putDouble(destOffset + 40, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putDouble(destOffset + 48, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        dest.putDouble(destOffset + 72, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putDouble(destOffset + 80, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return toMatrix_api_mulAdd_s51da9ef4_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_mulAdd_s51da9ef4_1(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset + 88, 0.0);
        dest.putDouble(destOffset + 96, 0.0);
        dest.putDouble(destOffset + 104, 0.0);
        dest.putDouble(destOffset + 112, 0.0);
        dest.putDouble(destOffset + 120, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 56, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    /** {@code toMatrix3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        dest.putDouble(destOffset + 24, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        dest.putDouble(destOffset + 32, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putDouble(destOffset + 40, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putDouble(destOffset + 48, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        dest.putDouble(destOffset + 56, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putDouble(destOffset + 64, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix3x4_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix3x4_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 72, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 80, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    /** {@code toMatrix3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix3x4_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        dest.putDouble(destOffset + 40, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putDouble(destOffset + 48, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        dest.putDouble(destOffset + 72, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putDouble(destOffset + 80, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        dest.putDouble(destOffset + 88, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_unsafe(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_api(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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
        swing.putDouble(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.putDouble(swingOffset + 8, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        swing.putDouble(swingOffset + 16, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_api_fma_sa865f540_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma_sa865f540_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 24, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.putDouble(twistOffset, _t12);
        twist.putDouble(twistOffset + 8, _t13);
        twist.putDouble(twistOffset + 16, _t14);
        twist.putDouble(twistOffset + 24, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        swing.putDouble(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.putDouble(swingOffset + 8, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.putDouble(swingOffset + 16, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_api_mulAdd_sb71928d9_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd_sb71928d9_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 24, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        twist.putDouble(twistOffset, _t12);
        twist.putDouble(twistOffset + 8, _t13);
        twist.putDouble(twistOffset + 16, _t14);
        twist.putDouble(twistOffset + 24, _t11);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_unsafe(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        DoubleQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, _axisBase);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_api(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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
        swing.putDouble(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.putDouble(swingOffset + 8, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return decomposeSwingTwist_api_fma_s729f480d_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma_s729f480d_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 16, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        swing.putDouble(swingOffset + 24, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.putDouble(twistOffset, _t12);
        twist.putDouble(twistOffset + 8, _t13);
        twist.putDouble(twistOffset + 16, _t14);
        twist.putDouble(twistOffset + 24, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        swing.putDouble(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.putDouble(swingOffset + 8, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.putDouble(swingOffset + 16, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_api_mulAdd_sab015e80_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd_sab015e80_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 24, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        twist.putDouble(twistOffset, _t12);
        twist.putDouble(twistOffset + 8, _t13);
        twist.putDouble(twistOffset + 16, _t14);
        twist.putDouble(twistOffset + 24, _t11);
        return swing;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        DoubleQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        dest.putDouble(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.putDouble(destOffset + 24, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        DoubleQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset);
        return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code getSwing_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_api_fma_s4addf26d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_fma_s4addf26d_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        dest.putDouble(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.putDouble(destOffset + 24, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        DoubleQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(axisZ, src.getDouble(srcOffset + 16), java.lang.Math.fma(axisX, src.getDouble(srcOffset), axisY * src.getDouble(srcOffset + 8)));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.putDouble(destOffset, axisX * _t6);
            dest.putDouble(destOffset + 8, axisY * _t6);
            dest.putDouble(destOffset + 16, axisZ * _t6);
            dest.putDouble(destOffset + 24, _selfw * _t5);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 1.0);
        }
        return dest;
    }

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getTwist_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((axisZ) * (src.getDouble(srcOffset + 16)) + (((axisX) * (src.getDouble(srcOffset)) + (axisY * src.getDouble(srcOffset + 8)))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.putDouble(destOffset, axisX * _t6);
            dest.putDouble(destOffset + 8, axisY * _t6);
            dest.putDouble(destOffset + 16, axisZ * _t6);
            dest.putDouble(destOffset + 24, _selfw * _t5);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 1.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        DoubleQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(_axisz, src.getDouble(srcOffset + 16), java.lang.Math.fma(_axisx, src.getDouble(srcOffset), _axisy * src.getDouble(srcOffset + 8)));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.putDouble(destOffset, _axisx * _t6);
            dest.putDouble(destOffset + 8, _axisy * _t6);
            dest.putDouble(destOffset + 16, _axisz * _t6);
            dest.putDouble(destOffset + 24, _selfw * _t5);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 1.0);
        }
        return dest;
    }

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getTwist_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((_axisz) * (src.getDouble(srcOffset + 16)) + (((_axisx) * (src.getDouble(srcOffset)) + (_axisy * src.getDouble(srcOffset + 8)))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.putDouble(destOffset, _axisx * _t6);
            dest.putDouble(destOffset + 8, _axisy * _t6);
            dest.putDouble(destOffset + 16, _axisz * _t6);
            dest.putDouble(destOffset + 24, _selfw * _t5);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 1.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.putDouble(destOffset + 8, ((t) * (otherY - _selfy) + (_selfy)));
            dest.putDouble(destOffset + 16, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.putDouble(destOffset + 24, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
        dest.putDouble(destOffset + 8, ((t) * (_othery - _selfy) + (_selfy)));
        dest.putDouble(destOffset + 16, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.putDouble(destOffset + 24, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, src.getDouble(srcOffset + 24) * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, src.getDouble(srcOffset + 16) * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, src.getDouble(srcOffset) * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, src.getDouble(srcOffset + 8) * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dest.putDouble(destOffset, _t7 * _t13);
            dest.putDouble(destOffset + 8, _t8 * _t13);
            dest.putDouble(destOffset + 16, _t6 * _t13);
            dest.putDouble(destOffset + 24, _t5 * _t13);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (targetW) + (src.getDouble(srcOffset + 24) * _t0));
        double _t6 = ((alpha) * (targetZ) + (src.getDouble(srcOffset + 16) * _t0));
        double _t7 = ((alpha) * (targetX) + (src.getDouble(srcOffset) * _t0));
        double _t8 = ((alpha) * (targetY) + (src.getDouble(srcOffset + 8) * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dest.putDouble(destOffset, _t7 * _t13);
            dest.putDouble(destOffset + 8, _t8 * _t13);
            dest.putDouble(destOffset + 16, _t6 * _t13);
            dest.putDouble(destOffset + 24, _t5 * _t13);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, target.getDouble(targetOffset + 24), src.getDouble(srcOffset + 24) * _t0);
        double _t6 = java.lang.Math.fma(alpha, target.getDouble(targetOffset + 16), src.getDouble(srcOffset + 16) * _t0);
        double _t7 = java.lang.Math.fma(alpha, target.getDouble(targetOffset), src.getDouble(srcOffset) * _t0);
        double _t8 = java.lang.Math.fma(alpha, target.getDouble(targetOffset + 8), src.getDouble(srcOffset + 8) * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dest.putDouble(destOffset, _t7 * _t13);
            dest.putDouble(destOffset + 8, _t8 * _t13);
            dest.putDouble(destOffset + 16, _t6 * _t13);
            dest.putDouble(destOffset + 24, _t5 * _t13);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (target.getDouble(targetOffset + 24)) + (src.getDouble(srcOffset + 24) * _t0));
        double _t6 = ((alpha) * (target.getDouble(targetOffset + 16)) + (src.getDouble(srcOffset + 16) * _t0));
        double _t7 = ((alpha) * (target.getDouble(targetOffset)) + (src.getDouble(srcOffset) * _t0));
        double _t8 = ((alpha) * (target.getDouble(targetOffset + 8)) + (src.getDouble(srcOffset + 8) * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            dest.putDouble(destOffset, _t7 * _t13);
            dest.putDouble(destOffset + 8, _t8 * _t13);
            dest.putDouble(destOffset + 16, _t6 * _t13);
            dest.putDouble(destOffset + 24, _t5 * _t13);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0) {
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
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_api_fma_sabdd9f25_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma_sabdd9f25_1(java.nio.ByteBuffer dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            dest.putDouble(destOffset, _t20 * _t26);
            dest.putDouble(destOffset + 8, _t21 * _t26);
            dest.putDouble(destOffset + 16, _t19 * _t26);
            dest.putDouble(destOffset + 24, _t18 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0) {
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
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_api_mulAdd_s1deb77a2_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd_s1deb77a2_1(java.nio.ByteBuffer dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            dest.putDouble(destOffset, _t20 * _t26);
            dest.putDouble(destOffset + 8, _t21 * _t26);
            dest.putDouble(destOffset + 16, _t19 * _t26);
            dest.putDouble(destOffset + 24, _t18 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0) {
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
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_api_fma_s27730d3b_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma_s27730d3b_1(java.nio.ByteBuffer dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            dest.putDouble(destOffset, _t20 * _t26);
            dest.putDouble(destOffset + 8, _t21 * _t26);
            dest.putDouble(destOffset + 16, _t19 * _t26);
            dest.putDouble(destOffset + 24, _t18 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0) {
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
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_api_mulAdd_scb677b0c_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd_scb677b0c_1(java.nio.ByteBuffer dest, int destOffset, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            dest.putDouble(destOffset, _t20 * _t26);
            dest.putDouble(destOffset + 8, _t21 * _t26);
            dest.putDouble(destOffset + 16, _t19 * _t26);
            dest.putDouble(destOffset + 24, _t18 * _t26);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfw + targetW;
        double _t3 = _selfz + targetZ;
        double _t4 = _selfx + targetX;
        double _t5 = _selfy + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        return slerp_api_fma_s6edebf2_1(dest, destOffset, targetY, targetZ, targetW, _selfy, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s6edebf2_1(java.nio.ByteBuffer dest, int destOffset, double targetY, double targetZ, double targetW, double _selfy, double _selfz, double _selfw, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfw + targetW;
        double _t3 = _selfz + targetZ;
        double _t4 = _selfx + targetX;
        double _t5 = _selfy + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.putDouble(destOffset, ((_selfx) * (_t25) + (targetX * _t26)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t25) + (targetY * _t26)));
        dest.putDouble(destOffset + 16, ((_selfz) * (_t25) + (targetZ * _t26)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest.putDouble(destOffset, ((_selfx) * (_t13) + (targetX * _t14)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t13) + (targetY * _t14)));
        dest.putDouble(destOffset + 16, ((_selfz) * (_t13) + (targetZ * _t14)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t13) + (targetW * _t14)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t2 = _selfw + _targetw;
        double _t3 = _selfz + _targetz;
        double _t4 = _selfx + _targetx;
        double _t5 = _selfy + _targety;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        return slerp_api_fma_s5ec90a9c_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s5ec90a9c_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _targetx, double _targety, double _targetz, double _targetw, double _t25, double _t26) {
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t2 = _selfw + _targetw;
        double _t3 = _selfz + _targetz;
        double _t4 = _selfx + _targetx;
        double _t5 = _selfy + _targety;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.putDouble(destOffset, ((_selfx) * (_t25) + (_targetx * _t26)));
        return slerp_api_mulAdd_s851dca71_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s851dca71_1(java.nio.ByteBuffer dest, int destOffset, double _selfy, double _selfz, double _selfw, double _targety, double _targetz, double _targetw, double _t25, double _t26) {
        dest.putDouble(destOffset + 8, ((_selfy) * (_t25) + (_targety * _t26)));
        dest.putDouble(destOffset + 16, ((_selfz) * (_t25) + (_targetz * _t26)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return DoubleQuatOpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        dest.putDouble(destOffset, ((_selfx) * (_t13) + (_targetx * _t14)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t13) + (_targety * _t14)));
        dest.putDouble(destOffset + 16, ((_selfz) * (_t13) + (_targetz * _t14)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_api_fma_s14a6c3b4_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma_s14a6c3b4_1(java.nio.ByteBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(_selfw, _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.putDouble(destOffset, _t51 * _t45);
            dest.putDouble(destOffset + 8, _t51 * _t46);
            dest.putDouble(destOffset + 16, _t51 * _t44);
            dest.putDouble(destOffset + 24, _t51 * _t43);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 1.0 - alpha;
        double _t12 = ((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        double _t33 = Math.sin(alpha * _t17) * _t32;
        double _t34 = Math.sin(_t0 * _t17) * _t32;
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((_selfw) * (_t34) + (_t33 * _t21));
            _t44 = ((_selfz) * (_t34) + (_t33 * _t22));
            _t45 = ((_selfx) * (_t34) + (_t33 * _t23));
            _t46 = ((_selfy) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t0));
            _t44 = ((alpha) * (_t22) + (_selfz * _t0));
            _t45 = ((alpha) * (_t23) + (_selfx * _t0));
            _t46 = ((alpha) * (_t24) + (_selfy * _t0));
        }
        return slerpShortest_api_mulAdd_s44b2c4ad_1(dest, destOffset, _t43, _t44, _t45, _t46);
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd_s44b2c4ad_1(java.nio.ByteBuffer dest, int destOffset, double _t43, double _t44, double _t45, double _t46) {
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.putDouble(destOffset, _t51 * _t45);
            dest.putDouble(destOffset + 8, _t51 * _t46);
            dest.putDouble(destOffset + 16, _t51 * _t44);
            dest.putDouble(destOffset + 24, _t51 * _t43);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_api_fma_sa9d04a36_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma_sa9d04a36_1(java.nio.ByteBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(_selfw, _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.putDouble(destOffset, _t51 * _t45);
            dest.putDouble(destOffset + 8, _t51 * _t46);
            dest.putDouble(destOffset + 16, _t51 * _t44);
            dest.putDouble(destOffset + 24, _t51 * _t43);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t0 = 1.0 - alpha;
        double _t12 = ((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        return slerpShortest_api_mulAdd_sd39232f3_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd_sd39232f3_1(java.nio.ByteBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((_selfw) * (_t34) + (_t33 * _t21));
            _t44 = ((_selfz) * (_t34) + (_t33 * _t22));
            _t45 = ((_selfx) * (_t34) + (_t33 * _t23));
            _t46 = ((_selfy) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t0));
            _t44 = ((alpha) * (_t22) + (_selfz * _t0));
            _t45 = ((alpha) * (_t23) + (_selfx * _t0));
            _t46 = ((alpha) * (_t24) + (_selfy * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.putDouble(destOffset, _t51 * _t45);
            dest.putDouble(destOffset + 8, _t51 * _t46);
            dest.putDouble(destOffset + 16, _t51 * _t44);
            dest.putDouble(destOffset + 24, _t51 * _t43);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer squad_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.ByteBuffer squad_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = Math.min(t, _t0);
        double _t23 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t27 = 4.0 - _t23;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = t + t;
        double _t12 = _t0 * _t1;
        return squad_api_fma_scd28aad0_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-14 ? java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33)) : 1.0 - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)));
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_scd28aad0_1(java.nio.ByteBuffer dest, int destOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t7, double _t41, double _t47, double _t51, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t8, double _t9, double _t10, double _t11, double _t12, double _t16) {
        double _t24 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        double _t28 = 4.0 - _t24;
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55 = _t41 > 2.0E-14 ? _t51 : _t47;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        return squad_api_fma_scd28aad0_2(dest, destOffset, _t12, _t16, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_selfz, _t61, targetZ * _t62), java.lang.Math.fma(_selfx, _t61, targetX * _t62), java.lang.Math.fma(control0Y, _t59, control1Y * _t60), java.lang.Math.fma(_selfy, _t61, targetY * _t62));
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_scd28aad0_2(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78) {
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = Math.min(t, _t0);
        double _t23 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t27 = 4.0 - _t23;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = t + t;
        double _t12 = _t0 * _t1;
        return squad_api_mulAdd_s5c79e289_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-14 ? ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0 - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_s5c79e289_1(java.nio.ByteBuffer dest, int destOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t7, double _t41, double _t47, double _t51, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t8, double _t9, double _t10, double _t11, double _t12, double _t16) {
        double _t24 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        double _t28 = 4.0 - _t24;
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55 = _t41 > 2.0E-14 ? _t51 : _t47;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t72 = ((control0W) * (_t59) + (control1W * _t60));
        double _t73 = ((_selfw) * (_t61) + (targetW * _t62));
        return squad_api_mulAdd_s5c79e289_2(dest, destOffset, _t12, _t16, ((control0X) * (_t59) + (control1X * _t60)), _t72, _t73, ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)), ((_selfy) * (_t61) + (targetY * _t62)), _t72 + _t73);
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_s5c79e289_2(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t79) {
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.putDouble(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.putDouble(destOffset + 8, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.putDouble(destOffset + 16, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.putDouble(destOffset + 24, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.nio.ByteBuffer squad_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _control0Base = UnsafeOpsHolder.U.getLong(control0, UnsafeCopy.BB_ADDRESS_OFFSET) + control0Offset;
        long _control1Base = UnsafeOpsHolder.U.getLong(control1, UnsafeCopy.BB_ADDRESS_OFFSET) + control1Offset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, _control0Base, _control1Base, _targetBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer squad_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _control0x = control0.getDouble(control0Offset);
        double _control0y = control0.getDouble(control0Offset + 8);
        double _control0z = control0.getDouble(control0Offset + 16);
        double _control0w = control0.getDouble(control0Offset + 24);
        double _control1x = control1.getDouble(control1Offset);
        double _control1y = control1.getDouble(control1Offset + 8);
        double _control1z = control1.getDouble(control1Offset + 16);
        double _control1w = control1.getDouble(control1Offset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t12 = _t0 * _t1;
        return squad_api_fma_s18c3452e_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)));
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s18c3452e_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t16) {
        double _t23 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t24 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_api_fma_s18c3452e_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s18c3452e_2(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t12, double _t16, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t71 = java.lang.Math.fma(_control0x, _t59, _control1x * _t60);
        double _t72 = java.lang.Math.fma(_control0w, _t59, _control1w * _t60);
        double _t73 = java.lang.Math.fma(_selfw, _t61, _targetw * _t62);
        double _t74 = java.lang.Math.fma(_control0z, _t59, _control1z * _t60);
        double _t75 = java.lang.Math.fma(_selfz, _t61, _targetz * _t62);
        double _t76 = java.lang.Math.fma(_selfx, _t61, _targetx * _t62);
        double _t77 = java.lang.Math.fma(_control0y, _t59, _control1y * _t60);
        double _t78 = java.lang.Math.fma(_selfy, _t61, _targety * _t62);
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        return squad_api_fma_s18c3452e_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0 - _t87);
    }

    /** Piece 4 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s18c3452e_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t87, double _t89) {
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _control0x = control0.getDouble(control0Offset);
        double _control0y = control0.getDouble(control0Offset + 8);
        double _control0z = control0.getDouble(control0Offset + 16);
        double _control0w = control0.getDouble(control0Offset + 24);
        double _control1x = control1.getDouble(control1Offset);
        double _control1y = control1.getDouble(control1Offset + 8);
        double _control1z = control1.getDouble(control1Offset + 16);
        double _control1w = control1.getDouble(control1Offset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t12 = _t0 * _t1;
        return squad_api_mulAdd_sfdae7d53_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_sfdae7d53_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t16) {
        double _t23 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t24 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_api_mulAdd_sfdae7d53_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_sfdae7d53_2(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t12, double _t16, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t71 = ((_control0x) * (_t59) + (_control1x * _t60));
        double _t72 = ((_control0w) * (_t59) + (_control1w * _t60));
        double _t73 = ((_selfw) * (_t61) + (_targetw * _t62));
        double _t74 = ((_control0z) * (_t59) + (_control1z * _t60));
        double _t75 = ((_selfz) * (_t61) + (_targetz * _t62));
        double _t76 = ((_selfx) * (_t61) + (_targetx * _t62));
        double _t77 = ((_control0y) * (_t59) + (_control1y * _t60));
        double _t78 = ((_selfy) * (_t61) + (_targety * _t62));
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        return squad_api_mulAdd_sfdae7d53_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0 - _t87);
    }

    /** Piece 4 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_sfdae7d53_3(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t87, double _t89) {
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.putDouble(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.putDouble(destOffset + 8, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.putDouble(destOffset + 16, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.putDouble(destOffset + 24, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        dest.putDouble(destOffset + 8, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        dest.putDouble(destOffset + 16, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        dest.putDouble(destOffset + 24, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        dest.putDouble(destOffset + 8, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        dest.putDouble(destOffset + 16, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        dest.putDouble(destOffset + 24, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        dest.putDouble(destOffset + 8, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        dest.putDouble(destOffset + 16, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        dest.putDouble(destOffset + 24, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        dest.putDouble(destOffset + 8, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        dest.putDouble(destOffset + 16, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        dest.putDouble(destOffset + 24, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(weight, otherX, _selfx));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(weight, otherY, _selfy));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(weight, otherZ, _selfz));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(weight, otherW, _selfw));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((weight) * (otherX) + (_selfx)));
            dest.putDouble(destOffset + 8, ((weight) * (otherY) + (_selfy)));
            dest.putDouble(destOffset + 16, ((weight) * (otherZ) + (_selfz)));
            dest.putDouble(destOffset + 24, ((weight) * (otherW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _otherx = other.getDouble(otherOffset);
            double _othery = other.getDouble(otherOffset + 8);
            double _otherz = other.getDouble(otherOffset + 16);
            double _otherw = other.getDouble(otherOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(weight, _otherx, _selfx));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(weight, _othery, _selfy));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(weight, _otherz, _selfz));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(weight, _otherw, _selfw));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _otherx = other.getDouble(otherOffset);
            double _othery = other.getDouble(otherOffset + 8);
            double _otherz = other.getDouble(otherOffset + 16);
            double _otherw = other.getDouble(otherOffset + 24);
            dest.putDouble(destOffset, ((weight) * (_otherx) + (_selfx)));
            dest.putDouble(destOffset + 8, ((weight) * (_othery) + (_selfy)));
            dest.putDouble(destOffset + 16, ((weight) * (_otherz) + (_selfz)));
            dest.putDouble(destOffset + 24, ((weight) * (_otherw) + (_selfw)));
            return dest;
        }
    }

    public static double angle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static double angle_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), src.getDouble(srcOffset + 24));
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), src.getDouble(srcOffset + 24));
        }
    }

    public static double angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleTo_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleTo_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static double angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static double angleTo_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleTo_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))) > 0.0) {
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
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static java.nio.ByteBuffer axis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer axis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
                dest.putDouble(destOffset, _selfx * _t3);
                dest.putDouble(destOffset + 8, _selfy * _t3);
                dest.putDouble(destOffset + 16, _selfz * _t3);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
            }
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0) {
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
    }

    public static java.nio.ByteBuffer calculateW_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer calculateW_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            dest.putDouble(destOffset, _selfx);
            dest.putDouble(destOffset + 8, _selfy);
            dest.putDouble(destOffset + 16, _selfz);
            dest.putDouble(destOffset + 24, java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0))))));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            dest.putDouble(destOffset, _selfx);
            dest.putDouble(destOffset + 8, _selfy);
            dest.putDouble(destOffset + 16, _selfz);
            dest.putDouble(destOffset + 24, java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer conjugate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer conjugate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -_selfx);
        dest.putDouble(destOffset + 8, -_selfy);
        dest.putDouble(destOffset + 16, -_selfz);
        dest.putDouble(destOffset + 24, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        double _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        double _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        double _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        dest.putDouble(destOffset, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        double _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        double _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        double _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        dest.putDouble(destOffset, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        dest.putDouble(destOffset + 8, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        dest.putDouble(destOffset + 16, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        dest.putDouble(destOffset + 24, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        DoubleQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        double _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        double _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        double _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        dest.putDouble(destOffset, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        return conjugateBy_api_fma_sfb1d3474_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma_sfb1d3474_1(java.nio.ByteBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        double _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        double _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        double _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        dest.putDouble(destOffset, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        dest.putDouble(destOffset + 8, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        return conjugateBy_api_mulAdd_sb7937283_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_mulAdd_sb7937283_1(java.nio.ByteBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        dest.putDouble(destOffset + 16, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        dest.putDouble(destOffset + 24, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.putDouble(destOffset + 16, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer difference_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        dest.putDouble(destOffset + 16, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        dest.putDouble(destOffset + 24, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.putDouble(destOffset + 16, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer difference_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        dest.putDouble(destOffset + 16, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        dest.putDouble(destOffset + 24, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.getDouble(srcOffset + 24), java.lang.Math.fma(otherZ, src.getDouble(srcOffset + 16), java.lang.Math.fma(otherX, src.getDouble(srcOffset), otherY * src.getDouble(srcOffset + 8))));
        } else {
            return ((otherW) * (src.getDouble(srcOffset + 24)) + (((otherZ) * (src.getDouble(srcOffset + 16)) + (((otherX) * (src.getDouble(srcOffset)) + (otherY * src.getDouble(srcOffset + 8)))))));
        }
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.getDouble(otherOffset + 24), src.getDouble(srcOffset + 24), java.lang.Math.fma(other.getDouble(otherOffset + 16), src.getDouble(srcOffset + 16), java.lang.Math.fma(other.getDouble(otherOffset), src.getDouble(srcOffset), other.getDouble(otherOffset + 8) * src.getDouble(srcOffset + 8))));
        } else {
            return ((other.getDouble(otherOffset + 24)) * (src.getDouble(srcOffset + 24)) + (((other.getDouble(otherOffset + 16)) * (src.getDouble(srcOffset + 16)) + (((other.getDouble(otherOffset)) * (src.getDouble(srcOffset)) + (other.getDouble(otherOffset + 8) * src.getDouble(srcOffset + 8)))))));
        }
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.min(Math.exp(src.getDouble(srcOffset + 24)), 1.7976931348623157E308);
        double _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0) {
            dest.putDouble(destOffset, _selfx * _t10);
            dest.putDouble(destOffset + 8, _selfy * _t10);
            dest.putDouble(destOffset + 16, _selfz * _t10);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer exp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.min(Math.exp(src.getDouble(srcOffset + 24)), 1.7976931348623157E308);
        double _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0) {
            dest.putDouble(destOffset, _selfx * _t10);
            dest.putDouble(destOffset + 8, _selfy * _t10);
            dest.putDouble(destOffset + 16, _selfz * _t10);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(_t11, _t14));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t12, _t13));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t12 = 2.0 * ((_selfx) * (_selfw) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(_t11, _t14));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t12, _t13));
            dest.putDouble(destOffset + 16, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0 * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.putDouble(destOffset, Math.atan2(_t11, _t14));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t12, _t13));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t12 = 2.0 * ((_selfx) * (_selfw) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.putDouble(destOffset, Math.atan2(_t11, _t14));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t12, _t13));
            dest.putDouble(destOffset + 16, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0 * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t10 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t12 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, Math.atan2(2.0 * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 8, Math.atan2(((2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.putDouble(destOffset, Math.atan2(2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t10 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t12 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.putDouble(destOffset, Math.atan2(2.0 * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 8, Math.atan2(((2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t10 = 2.0 * ((_selfx) * (_selfw) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t12 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 16, Math.atan2(((_t9) * (2.0 * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        double _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 16, Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t10 = 2.0 * ((_selfx) * (_selfw) - (_t0));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t12 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_t9, _t11));
            dest.putDouble(destOffset + 16, Math.atan2(((_t9) * (2.0 * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t2);
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t13 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_t12, _t15));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.putDouble(destOffset + 8, Math.atan2(_t13, _t14));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_t2));
        double _t12 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t13 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t15 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_t12, _t15));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_t14) * (2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0 * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.putDouble(destOffset + 8, Math.atan2(_t13, _t14));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t2);
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t13 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        double _t15 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_t12, _t15));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.putDouble(destOffset + 8, Math.atan2(_t13, _t14));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_t2));
        double _t12 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t13 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t15 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(_t12, _t15));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_t14) * (2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0 * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.putDouble(destOffset + 8, Math.atan2(_t13, _t14));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t12 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_t11, _t14));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.putDouble(destOffset + 16, Math.atan2(_t12, _t13));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t12 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_t11, _t14));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.putDouble(destOffset + 16, Math.atan2(_t12, _t13));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t11 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t12 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0);
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0);
        double _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_t11, _t14));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.putDouble(destOffset + 16, Math.atan2(_t12, _t13));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t12 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(_t11, _t14));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.putDouble(destOffset + 16, Math.atan2(_t12, _t13));
        }
        dest.putDouble(destOffset, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1);
        double _t9 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.putDouble(destOffset + 16, Math.atan2(_t8, _t10));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * ((_selfx) * (_selfy) + (_t1));
        double _t9 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t10 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 7.888609052210118E-31) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_t8) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((_selfx) * (_selfy) - (_t1))))));
            dest.putDouble(destOffset + 16, Math.atan2(_t8, _t10));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1);
        double _t9 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0);
        double _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.putDouble(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.putDouble(destOffset + 16, Math.atan2(_t8, _t10));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * ((_selfx) * (_selfy) + (_t1));
        double _t9 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t10 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.putDouble(destOffset, Math.atan2(((_t8) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((_selfx) * (_selfy) - (_t1))))));
            dest.putDouble(destOffset + 16, Math.atan2(_t8, _t10));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, double dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _angularVelBase = UnsafeOpsHolder.U.getLong(angularVel, UnsafeCopy.BB_ADDRESS_OFFSET) + angularVelOffset;
        DoubleQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, _angularVelBase, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, double dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, double dt) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * dt;
        double _t1 = angularVel.getDouble(angularVelOffset + 16) * _t0;
        double _t2 = angularVel.getDouble(angularVelOffset) * _t0;
        double _t3 = angularVel.getDouble(angularVelOffset + 8) * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_api_fma_s2996bfeb_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma_s2996bfeb_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t15, double _t16, double _t17) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, double dt) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * dt;
        double _t1 = angularVel.getDouble(angularVelOffset + 16) * _t0;
        double _t2 = angularVel.getDouble(angularVelOffset) * _t0;
        double _t3 = angularVel.getDouble(angularVelOffset + 8) * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
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
        dest.putDouble(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t12 * _t16));
            dest.putDouble(destOffset + 8, -(_t10 * _t16));
            dest.putDouble(destOffset + 16, -(_t9 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t12 * _t16));
            dest.putDouble(destOffset + 8, -(_t10 * _t16));
            dest.putDouble(destOffset + 16, -(_t9 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t9 * _t16));
            dest.putDouble(destOffset + 8, -(_t12 * _t16));
            dest.putDouble(destOffset + 16, -(_t10 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t9 * _t16));
            dest.putDouble(destOffset + 8, -(_t12 * _t16));
            dest.putDouble(destOffset + 16, -(_t10 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t10 * _t16));
            dest.putDouble(destOffset + 8, -(_t9 * _t16));
            dest.putDouble(destOffset + 16, -(_t12 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t10 * _t16));
            dest.putDouble(destOffset + 8, -(_t9 * _t16));
            dest.putDouble(destOffset + 16, -(_t12 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
            dest.putDouble(destOffset + 8, -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putDouble(destOffset + 16, -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0)));
            dest.putDouble(destOffset + 8, -2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putDouble(destOffset + 16, -2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
            dest.putDouble(destOffset + 16, -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putDouble(destOffset + 8, ((2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0)));
            dest.putDouble(destOffset + 16, -2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.putDouble(destOffset + 8, -2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.putDouble(destOffset + 8, -2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.putDouble(destOffset + 16, ((2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
            dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
            dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putDouble(destOffset + 8, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.putDouble(destOffset + 16, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.putDouble(destOffset + 16, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t12 * _t16);
            dest.putDouble(destOffset + 8, _t10 * _t16);
            dest.putDouble(destOffset + 16, _t9 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t12 * _t16);
            dest.putDouble(destOffset + 8, _t10 * _t16);
            dest.putDouble(destOffset + 16, _t9 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t9 * _t16);
            dest.putDouble(destOffset + 8, _t12 * _t16);
            dest.putDouble(destOffset + 16, _t10 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t9 * _t16);
            dest.putDouble(destOffset + 8, _t12 * _t16);
            dest.putDouble(destOffset + 16, _t10 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t10 * _t16);
            dest.putDouble(destOffset + 8, _t9 * _t16);
            dest.putDouble(destOffset + 16, _t12 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t10 * _t16);
            dest.putDouble(destOffset + 8, _t9 * _t16);
            dest.putDouble(destOffset + 16, _t12 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static double length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            return java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static double lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
            dest.putDouble(destOffset, _selfx * _t6);
            dest.putDouble(destOffset + 8, _selfy * _t6);
            dest.putDouble(destOffset + 16, _selfz * _t6);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer log_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
            dest.putDouble(destOffset, _selfx * _t6);
            dest.putDouble(destOffset + 8, _selfy * _t6);
            dest.putDouble(destOffset + 16, _selfz * _t6);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t12 * _t16));
            dest.putDouble(destOffset + 8, -(_t9 * _t16));
            dest.putDouble(destOffset + 16, -(_t10 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code negativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t12 * _t16));
            dest.putDouble(destOffset + 8, -(_t9 * _t16));
            dest.putDouble(destOffset + 16, -(_t10 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t10 * _t16));
            dest.putDouble(destOffset + 8, -(_t12 * _t16));
            dest.putDouble(destOffset + 16, -(_t9 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code negativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t10 * _t16));
            dest.putDouble(destOffset + 8, -(_t12 * _t16));
            dest.putDouble(destOffset + 16, -(_t9 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t9 * _t16));
            dest.putDouble(destOffset + 8, -(_t10 * _t16));
            dest.putDouble(destOffset + 16, -(_t12 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    /** {@code negativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, -(_t9 * _t16));
            dest.putDouble(destOffset + 8, -(_t10 * _t16));
            dest.putDouble(destOffset + 16, -(_t12 * _t16));
        } else {
            dest.putDouble(destOffset, -0.0);
            dest.putDouble(destOffset + 8, -0.0);
            dest.putDouble(destOffset + 16, -0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t4);
            dest.putDouble(destOffset + 8, _selfy * _t4);
            dest.putDouble(destOffset + 16, _selfz * _t4);
            dest.putDouble(destOffset + 24, _selfw * _t4);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normalize_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.putDouble(destOffset, _selfx * _t4);
            dest.putDouble(destOffset + 8, _selfy * _t4);
            dest.putDouble(destOffset + 16, _selfz * _t4);
            dest.putDouble(destOffset + 24, _selfw * _t4);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
            dest.putDouble(destOffset + 8, -2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putDouble(destOffset + 16, -2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0)));
            dest.putDouble(destOffset + 8, -2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putDouble(destOffset + 16, -2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
            dest.putDouble(destOffset + 16, -2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putDouble(destOffset + 8, ((2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0)));
            dest.putDouble(destOffset + 16, -2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.putDouble(destOffset + 8, -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, -2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.putDouble(destOffset + 8, -2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.putDouble(destOffset + 16, ((2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
            dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.putDouble(destOffset + 8, 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
            dest.putDouble(destOffset + 16, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putDouble(destOffset + 8, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.putDouble(destOffset + 16, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.putDouble(destOffset + 8, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.putDouble(destOffset + 8, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.putDouble(destOffset + 16, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t12 * _t16);
            dest.putDouble(destOffset + 8, _t9 * _t16);
            dest.putDouble(destOffset + 16, _t10 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code positiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t12 * _t16);
            dest.putDouble(destOffset + 8, _t9 * _t16);
            dest.putDouble(destOffset + 16, _t10 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t10 * _t16);
            dest.putDouble(destOffset + 8, _t12 * _t16);
            dest.putDouble(destOffset + 16, _t9 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code positiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t10 * _t16);
            dest.putDouble(destOffset + 8, _t12 * _t16);
            dest.putDouble(destOffset + 16, _t9 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t9 * _t16);
            dest.putDouble(destOffset + 8, _t10 * _t16);
            dest.putDouble(destOffset + 16, _t12 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    /** {@code positiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.putDouble(destOffset, _t9 * _t16);
            dest.putDouble(destOffset + 8, _t10 * _t16);
            dest.putDouble(destOffset + 16, _t12 * _t16);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.pow_unsafe(_destBase, _srcBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_api_fma(dest, destOffset, src, srcOffset, t);
        return pow_api_mulAdd(dest, destOffset, src, srcOffset, t);
    }

    /** {@code pow_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer pow_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))), 1.7976931348623157E308);
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
        double _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        return pow_api_fma_s593ff008_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer pow_api_fma_s593ff008_1(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t20, double _t21, double _t22, double _t25, double _t26, double _t31) {
        if (_t25 > 0.0) {
            dest.putDouble(destOffset, _t21 * _t31);
            dest.putDouble(destOffset + 8, _t22 * _t31);
            dest.putDouble(destOffset + 16, _t20 * _t31);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer pow_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))), 1.7976931348623157E308);
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
        double _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0) {
            dest.putDouble(destOffset, _t21 * _t31);
            dest.putDouble(destOffset + 8, _t22 * _t31);
            dest.putDouble(destOffset + 16, _t20 * _t31);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        return rotateTowards_api_fma_s83caf0a5_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), 4.0 * _t34, _t34 + _t34);
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s83caf0a5_1(java.nio.ByteBuffer dest, int destOffset, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t36, double _t37) {
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(_selfw, _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(_selfz, _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(_selfx, _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(_selfy, _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(_selfw, _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(_selfz, _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(_selfx, _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(_selfy, _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        return rotateTowards_api_fma_s83caf0a5_2(dest, destOffset, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s83caf0a5_2(java.nio.ByteBuffer dest, int destOffset, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            dest.putDouble(destOffset, _t74 * _t68);
            dest.putDouble(destOffset + 8, _t74 * _t69);
            dest.putDouble(destOffset + 16, _t74 * _t67);
            dest.putDouble(destOffset + 24, _t74 * _t66);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        return rotateTowards_api_mulAdd_s4e4de7c_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), _t34 + _t34, _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0);
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s4e4de7c_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t37, double _t40) {
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((_selfw) * (_t53) + (_t52 * _t9));
            _t67 = ((_selfz) * (_t53) + (_t52 * _t10));
            _t68 = ((_selfx) * (_t53) + (_t52 * _t11));
            _t69 = ((_selfy) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((_selfw) * (_t41) + (_t9 * _t40));
            _t67 = ((_selfz) * (_t41) + (_t10 * _t40));
            _t68 = ((_selfx) * (_t41) + (_t11 * _t40));
            _t69 = ((_selfy) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        double _t74 = (1.0 / java.lang.Math.sqrt(_t73));
        if (_t73 != 0.0) {
            dest.putDouble(destOffset, _t74 * _t68);
            dest.putDouble(destOffset + 8, _t74 * _t69);
            dest.putDouble(destOffset + 16, _t74 * _t67);
            dest.putDouble(destOffset + 24, _t74 * _t66);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        DoubleQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _targetBase, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double step) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        return rotateTowards_api_fma_sf880787b_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t27, _t28, _t27 * _t28);
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sf880787b_1(java.nio.ByteBuffer dest, int destOffset, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t27, double _t28, double _t31) {
        double _sp0 = 2.0 * (1.0 / java.lang.Math.sqrt(_t31));
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t37 = _t34 + _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(_selfw, _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(_selfz, _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(_selfx, _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(_selfy, _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(_selfw, _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(_selfz, _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(_selfx, _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(_selfy, _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        return rotateTowards_api_fma_sf880787b_2(dest, destOffset, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sf880787b_2(java.nio.ByteBuffer dest, int destOffset, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            dest.putDouble(destOffset, _t74 * _t68);
            dest.putDouble(destOffset + 8, _t74 * _t69);
            dest.putDouble(destOffset + 16, _t74 * _t67);
            dest.putDouble(destOffset + 24, _t74 * _t66);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, double step) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
        double _t9, _t10, _t11, _t12;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        return rotateTowards_api_mulAdd_s78e47bb6_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t27, _t28, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s78e47bb6_1(java.nio.ByteBuffer dest, int destOffset, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t27, double _t28, double _t31, double _sp0) {
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t37 = _t34 + _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((_selfw) * (_t53) + (_t52 * _t9));
            _t67 = ((_selfz) * (_t53) + (_t52 * _t10));
            _t68 = ((_selfx) * (_t53) + (_t52 * _t11));
            _t69 = ((_selfy) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((_selfw) * (_t41) + (_t9 * _t40));
            _t67 = ((_selfz) * (_t41) + (_t10 * _t40));
            _t68 = ((_selfx) * (_t41) + (_t11 * _t40));
            _t69 = ((_selfy) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        return rotateTowards_api_mulAdd_s78e47bb6_2(dest, destOffset, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_s78e47bb6_2(java.nio.ByteBuffer dest, int destOffset, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            dest.putDouble(destOffset, _t74 * _t68);
            dest.putDouble(destOffset + 8, _t74 * _t69);
            dest.putDouble(destOffset + 16, _t74 * _t67);
            dest.putDouble(destOffset + 24, _t74 * _t66);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_api_fma_sb3012894_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb3012894_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_api_fma_sb3012894_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb3012894_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_sb3012894_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb3012894_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_api_mulAdd_s5ebb6655_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s5ebb6655_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_api_mulAdd_s5ebb6655_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s5ebb6655_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s5ebb6655_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s5ebb6655_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_api_fma_sf558d4e2_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf558d4e2_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_api_fma_sf558d4e2_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf558d4e2_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_api_fma_sf558d4e2_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf558d4e2_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_api_mulAdd_s36ebbd63_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s36ebbd63_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return lookAlong_rh_api_mulAdd_s36ebbd63_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s36ebbd63_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s36ebbd63_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s36ebbd63_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_api_fma_sb591f666_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        return lookAlong_lh_api_fma_sb591f666_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_sb591f666_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        return lookAlong_rh_api_fma_se523af34_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        return lookAlong_rh_api_fma_se523af34_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t44, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75) {
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_api_fma_se523af34_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_api_mulAdd_s9643727b_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_api_mulAdd_s9643727b_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s9643727b_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_api_fma_sb08e4d77_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb08e4d77_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_api_fma_sb08e4d77_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb08e4d77_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_sb08e4d77_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb08e4d77_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_api_mulAdd_s9160dca6_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s9160dca6_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_api_mulAdd_s9160dca6_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s9160dca6_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s9160dca6_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s9160dca6_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_api_fma_s66fbaba9_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s66fbaba9_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return lookAlong_rh_api_fma_s66fbaba9_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s66fbaba9_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_api_fma_s66fbaba9_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s66fbaba9_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_api_mulAdd_sa5ecffb0_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sa5ecffb0_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return lookAlong_rh_api_mulAdd_sa5ecffb0_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sa5ecffb0_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_sa5ecffb0_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sa5ecffb0_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_api_fma_sb01e715d_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb01e715d_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        return lookAlong_lh_api_fma_sb01e715d_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb01e715d_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_sb01e715d_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb01e715d_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_api_mulAdd_sb74a88b6_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sb74a88b6_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_api_mulAdd_sb74a88b6_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sb74a88b6_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_sb74a88b6_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sb74a88b6_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        return lookAlong_rh_api_fma_sf7d6f517_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf7d6f517_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        return lookAlong_rh_api_fma_sf7d6f517_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf7d6f517_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t44, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75) {
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_api_fma_sf7d6f517_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_sf7d6f517_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_api_mulAdd_s583a3f18_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s583a3f18_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_api_mulAdd_s583a3f18_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s583a3f18_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.putDouble(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s583a3f18_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s583a3f18_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dest.putDouble(destOffset, axisX * _t1);
        dest.putDouble(destOffset + 8, axisY * _t1);
        dest.putDouble(destOffset + 16, axisZ * _t1);
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        DoubleQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        dest.putDouble(destOffset, _axisx * _t1);
        dest.putDouble(destOffset + 8, _axisy * _t1);
        dest.putDouble(destOffset + 16, _axisz * _t1);
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_api_fma_s3f97fbc8_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3f97fbc8_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_api_fma_s3f97fbc8_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3f97fbc8_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49, double _t60) {
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se21d4cf9_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_api_fma_s48e198b6_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s48e198b6_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return makeRotationLookAlong_rh_api_fma_s48e198b6_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s48e198b6_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51, double _t62) {
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s75d14d6f_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_api_fma_sef1062f2_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_api_fma_sef1062f2_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_api_fma_s3960771b_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3960771b_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_api_fma_s3960771b_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s3960771b_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_api_mulAdd_s769baa6a_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s769baa6a_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49, double _t60) {
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_s769baa6a_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s769baa6a_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
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
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_api_fma_s30114dcd_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s30114dcd_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        return makeRotationLookAlong_rh_api_fma_s30114dcd_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s30114dcd_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
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
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_api_mulAdd_saf09d8c_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_saf09d8c_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51, double _t62) {
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_saf09d8c_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_saf09d8c_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_api_fma_sbf22fc99_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sbf22fc99_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_api_fma_sbf22fc99_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sbf22fc99_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_api_mulAdd_s57c6b232_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s57c6b232_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_s57c6b232_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s57c6b232_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t65);
            dest.putDouble(destOffset + 8, _sp0 * _t49);
            dest.putDouble(destOffset + 16, _sp0 * _t69);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t72));
                dest.putDouble(destOffset + 8, _sp3 * _t68);
                dest.putDouble(destOffset + 16, _sp3 * _t48);
                dest.putDouble(destOffset + 24, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t68);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t73));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t49);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t48);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 24, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return DoubleQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_api_fma_s100a78b3_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s100a78b3_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return makeRotationLookAlong_rh_api_fma_s100a78b3_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s100a78b3_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, double epsilon) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
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
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sfb3028ac_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t51);
            dest.putDouble(destOffset + 16, _sp0 * _t71);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t74));
                dest.putDouble(destOffset + 8, _sp3 * _t70);
                dest.putDouble(destOffset + 16, _sp3 * _t50);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putDouble(destOffset, _sp1 * _t70);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 16, _sp1 * _t68);
                    dest.putDouble(destOffset + 24, _sp1 * _t51);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t50);
                    dest.putDouble(destOffset + 8, _sp2 * _t68);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t76));
                    dest.putDouble(destOffset + 24, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
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
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_sbc03e5e3_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_sbc03e5e3_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
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
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s6a25908c_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s6a25908c_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
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
        double _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        double _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_s2cf4665c_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_s2cf4665c_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
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
        double _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        double _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        double _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s469c8e4f_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s469c8e4f_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
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
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_s1b204830_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_s1b204830_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
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
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s4ba5c63f_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s4ba5c63f_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
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
        double _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        double _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_sc3fabdef_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_sc3fabdef_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
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
        double _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        double _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        double _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s26d9d9ec_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s26d9d9ec_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            dest.putDouble(destOffset, _t15 * _t32);
            dest.putDouble(destOffset + 8, _t17 * _t32);
            dest.putDouble(destOffset + 16, _t16 * _t32);
            dest.putDouble(destOffset + 24, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.putDouble(destOffset, _t30 * _t13);
                dest.putDouble(destOffset + 8, _t30 * _t19);
                dest.putDouble(destOffset + 16, _t30 * _t18);
                dest.putDouble(destOffset + 24, 0.0);
            } else {
                dest.putDouble(destOffset, 0.0);
                dest.putDouble(destOffset + 8, 0.0);
                dest.putDouble(destOffset + 16, 0.0);
                dest.putDouble(destOffset + 24, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        dest.putDouble(destOffset, Math.sinWithCos(_t0));
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_api_mulAdd(dest, destOffset, angleX, angleY, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dest.putDouble(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.putDouble(destOffset + 16, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putDouble(destOffset + 24, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_api_mulAdd(dest, destOffset, angleX, angleZ, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dest.putDouble(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.putDouble(destOffset + 16, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.putDouble(destOffset + 24, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, Math.sinWithCos(_t0));
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_api_mulAdd(dest, destOffset, angleY, angleX, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        dest.putDouble(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.putDouble(destOffset + 16, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.putDouble(destOffset + 24, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_api_mulAdd(dest, destOffset, angleY, angleZ, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        dest.putDouble(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putDouble(destOffset + 16, ((_t12) * (_t5) - (_t11 * _t8)));
        dest.putDouble(destOffset + 24, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, Math.sinWithCos(_t0));
        dest.putDouble(destOffset + 24, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_api_mulAdd(dest, destOffset, angleZ, angleX, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        dest.putDouble(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putDouble(destOffset + 16, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.putDouble(destOffset + 24, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_api_mulAdd(dest, destOffset, angleZ, angleY, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dest.putDouble(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        dest.putDouble(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putDouble(destOffset + 8, ((_t11) * (_t8) + (_t12 * _t5)));
        dest.putDouble(destOffset + 16, ((_t12) * (_t8) - (_t11 * _t5)));
        dest.putDouble(destOffset + 24, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 8, ((_selfy) * (_t1) - (_selfz * _t2)));
            dest.putDouble(destOffset + 16, ((_selfy) * (_t2) + (_selfz * _t1)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfz * _t2)));
            dest.putDouble(destOffset + 8, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 16, ((_selfz) * (_t1) - (_selfx * _t2)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) - (_selfy * _t2)));
            dest.putDouble(destOffset + 8, ((_selfx) * (_t2) + (_selfy * _t1)));
            dest.putDouble(destOffset + 16, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        DoubleQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axis.getDouble(axisOffset) * _t2;
        double _t4 = axis.getDouble(axisOffset + 16) * _t2;
        double _t5 = axis.getDouble(axisOffset + 8) * _t2;
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axis.getDouble(axisOffset) * _t2;
        double _t4 = axis.getDouble(axisOffset + 16) * _t2;
        double _t5 = axis.getDouble(axisOffset + 8) * _t2;
        dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_sd45a7b3a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_sd45a7b3a_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_se7b7eebf_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_se7b7eebf_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
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
        dest.putDouble(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_see328ad5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_see328ad5_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_sa8bf2eb0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_sa8bf2eb0_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
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
        dest.putDouble(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_s8d7b3b51_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_s8d7b3b51_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_sf50c447c_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_sf50c447c_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        dest.putDouble(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_s78aa2126_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_s78aa2126_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, double epsilon) {
        double _fromDirx = fromDir.getDouble(fromDirOffset);
        double _fromDiry = fromDir.getDouble(fromDirOffset + 8);
        double _fromDirz = fromDir.getDouble(fromDirOffset + 16);
        double _toDirx = toDir.getDouble(toDirOffset);
        double _toDiry = toDir.getDouble(toDirOffset + 8);
        double _toDirz = toDir.getDouble(toDirOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_s93e88dd3_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_s93e88dd3_1(java.nio.ByteBuffer dest, int destOffset, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        dest.putDouble(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 8, ((_selfy) * (_t1) + (_selfz * _t2)));
            dest.putDouble(destOffset + 16, ((_selfz) * (_t1) - (_selfy * _t2)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXYZ_api_fma_see905d02_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma_see905d02_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dest.putDouble(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXYZ_api_mulAdd_s78b80749_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd_s78b80749_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXZY_api_fma_se99e10f2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma_se99e10f2_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        double _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dest.putDouble(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXZY_api_mulAdd_s6bf69a59_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd_s6bf69a59_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) - (_selfz * _t2)));
            dest.putDouble(destOffset + 8, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 16, ((_selfx) * (_t2) + (_selfz * _t1)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        return rotateYXZ_api_fma_s544792f2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma_s544792f2_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dest.putDouble(destOffset, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        return rotateYXZ_api_mulAdd_s152fcba5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd_s152fcba5_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateYZX_api_fma_sa75c85b2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma_sa75c85b2_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.putDouble(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateYZX_api_mulAdd_scd14a6b1_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd_scd14a6b1_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
            dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            double _selfx = src.getDouble(srcOffset);
            double _selfy = src.getDouble(srcOffset + 8);
            double _selfz = src.getDouble(srcOffset + 16);
            double _selfw = src.getDouble(srcOffset + 24);
            double _t0 = 0.5 * angle;
            double _t1 = Math.cosWithSin(_t0);
            double _t2 = Math.sinWithCos(_t0);
            dest.putDouble(destOffset, ((_selfx) * (_t1) + (_selfy * _t2)));
            dest.putDouble(destOffset + 8, ((_selfy) * (_t1) - (_selfx * _t2)));
            dest.putDouble(destOffset + 16, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.putDouble(destOffset + 24, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        return rotateZXY_api_fma_s9244902_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma_s9244902_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.putDouble(destOffset, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        return rotateZXY_api_mulAdd_sf4200725_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd_sf4200725_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateZYX_api_fma_s2ba94e2a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma_s2ba94e2a_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dest.putDouble(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.putDouble(destOffset + 8, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateZYX_api_mulAdd_s41701511_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd_s41701511_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.putDouble(destOffset + 24, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (vY) - (_selfy * vX));
        double _t10 = 2.0 * ((_selfz) * (vX) - (_selfx * vZ));
        double _t11 = 2.0 * ((_selfy) * (vZ) - (_selfz * vY));
        dest.putDouble(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        dest.putDouble(destOffset + 8, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        DoubleQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        double _t11 = 2.0 * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_vy) - (_selfy * _vx));
        double _t10 = 2.0 * ((_selfz) * (_vx) - (_selfx * _vz));
        double _t11 = 2.0 * ((_selfy) * (_vz) - (_selfz * _vy));
        dest.putDouble(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        dest.putDouble(destOffset + 8, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        dest.putDouble(destOffset + 16, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        double _t11 = 2.0 * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transformInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (vZ) - (_selfz * vX));
        double _t10 = 2.0 * ((_selfy) * (vX) - (_selfx * vY));
        double _t11 = 2.0 * ((_selfz) * (vY) - (_selfy * vZ));
        dest.putDouble(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        DoubleQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        double _t11 = 2.0 * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.putDouble(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transformInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * ((_selfx) * (_vz) - (_selfz * _vx));
        double _t10 = 2.0 * ((_selfy) * (_vx) - (_selfx * _vy));
        double _t11 = 2.0 * ((_selfz) * (_vy) - (_selfy * _vz));
        dest.putDouble(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        dest.putDouble(destOffset + 8, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        dest.putDouble(destOffset + 16, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
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
