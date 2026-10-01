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
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code DoubleQuatOps} and its sibling kernel units. Not public API.
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest.putDouble(destOffset, -(_selfx * _t3_inv));
        dest.putDouble(destOffset + 8, -(_selfy * _t3_inv));
        dest.putDouble(destOffset + 16, -(_selfz * _t3_inv));
        dest.putDouble(destOffset + 24, _selfw * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        double _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        double _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        double _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        double _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
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
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u1);
        double _t6 = Math.sin(_t3);
        dest.putDouble(destOffset, _t4 * _t5);
        dest.putDouble(destOffset + 8, Math.cosFromSin(_t4, _t1) * _t5);
        dest.putDouble(destOffset + 16, _t6 * _t0);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t6, _t3) * _t0);
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return toMatrix_api_s9b3d2c84_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_s9b3d2c84_1(java.nio.ByteBuffer dest, int destOffset) {
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 24, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 32, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 40, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 56, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 64, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.putDouble(destOffset + 24, 0.0);
        dest.putDouble(destOffset + 32, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.putDouble(destOffset + 40, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.putDouble(destOffset + 48, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.putDouble(destOffset + 72, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 80, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        swing.putDouble(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.putDouble(swingOffset + 8, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        swing.putDouble(swingOffset + 16, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        return decomposeSwingTwist_api_s3da31371_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_s3da31371_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 24, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
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
        swing.putDouble(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.putDouble(swingOffset + 8, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return decomposeSwingTwist_api_s5feaaa58_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_s5feaaa58_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.putDouble(swingOffset + 16, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        swing.putDouble(swingOffset + 24, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.putDouble(destOffset + 24, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        DoubleQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return getSwing_api_s32840fb0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_s32840fb0_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.putDouble(destOffset + 24, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        DoubleQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = Math.fma(axisZ, src.getDouble(srcOffset + 16), Math.fma(axisX, src.getDouble(srcOffset), axisY * src.getDouble(srcOffset + 8)));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        double _selfw = src.getDouble(srcOffset + 24);
        double _axisx = axis.getDouble(axisOffset);
        double _axisy = axis.getDouble(axisOffset + 8);
        double _axisz = axis.getDouble(axisOffset + 16);
        double _t2 = Math.fma(_axisz, src.getDouble(srcOffset + 16), Math.fma(_axisx, src.getDouble(srcOffset), _axisy * src.getDouble(srcOffset + 8)));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, otherY - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, otherZ - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(t, _othery - _selfy, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(t, _otherz - _selfz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        double _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        double _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        double _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            dest.putDouble(destOffset, _t6 * _t12);
            dest.putDouble(destOffset + 8, _t7 * _t12);
            dest.putDouble(destOffset + 16, _t5 * _t12);
            dest.putDouble(destOffset + 24, _t4 * _t12);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t4 = Math.fma(alpha, target.getDouble(targetOffset + 24) - _selfw, _selfw);
        double _t5 = Math.fma(alpha, target.getDouble(targetOffset + 16) - _selfz, _selfz);
        double _t6 = Math.fma(alpha, target.getDouble(targetOffset) - _selfx, _selfx);
        double _t7 = Math.fma(alpha, target.getDouble(targetOffset + 8) - _selfy, _selfy);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            dest.putDouble(destOffset, _t6 * _t12);
            dest.putDouble(destOffset + 8, _t7 * _t12);
            dest.putDouble(destOffset + 16, _t5 * _t12);
            dest.putDouble(destOffset + 24, _t4 * _t12);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        return nlerpShortest_api_s1659e940_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0 / java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_s1659e940_1(java.nio.ByteBuffer dest, int destOffset, double _t17, double _t18, double _t19, double _t20, double _t24, double _t25) {
        if (_t24 != 0.0) {
            dest.putDouble(destOffset, _t19 * _t25);
            dest.putDouble(destOffset + 8, _t20 * _t25);
            dest.putDouble(destOffset + 16, _t18 * _t25);
            dest.putDouble(destOffset + 24, _t17 * _t25);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
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
        return nlerpShortest_api_s1ea8c77a_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0 / java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_s1ea8c77a_1(java.nio.ByteBuffer dest, int destOffset, double _t17, double _t18, double _t19, double _t20, double _t24, double _t25) {
        if (_t24 != 0.0) {
            dest.putDouble(destOffset, _t19 * _t25);
            dest.putDouble(destOffset + 8, _t20 * _t25);
            dest.putDouble(destOffset + 16, _t18 * _t25);
            dest.putDouble(destOffset + 24, _t17 * _t25);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t27, targetX * _t26));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t27, targetY * _t26));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t27, targetZ * _t26));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t27, targetW * _t26));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t27, _targetx * _t26));
        return slerp_api_s15166251_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t26, _t27);
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_s15166251_1(java.nio.ByteBuffer dest, int destOffset, double _selfy, double _selfz, double _selfw, double _targety, double _targetz, double _targetw, double _t26, double _t27) {
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t27, _targety * _t26));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t27, _targetz * _t26));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t27, _targetw * _t26));
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        return slerpShortest_api_s16a488f5_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0 / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_s16a488f5_1(java.nio.ByteBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
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
            dest.putDouble(destOffset, _t50 * _t44);
            dest.putDouble(destOffset + 8, _t50 * _t45);
            dest.putDouble(destOffset + 16, _t50 * _t43);
            dest.putDouble(destOffset + 24, _t50 * _t42);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
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
        return slerpShortest_api_sc548c38b_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0 / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_sc548c38b_1(java.nio.ByteBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
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
            dest.putDouble(destOffset, _t50 * _t44);
            dest.putDouble(destOffset + 8, _t50 * _t45);
            dest.putDouble(destOffset + 16, _t50 * _t43);
            dest.putDouble(destOffset + 24, _t50 * _t42);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return squad_api_sf769acb1_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, t + t, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY);
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_sf769acb1_1(java.nio.ByteBuffer dest, int destOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t0, double _t7, double _t12, double _t55, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t8, double _t9, double _t10, double _t11) {
        double _t13 = _t0 * _t1;
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
        return squad_api_sf769acb1_2(dest, destOffset, _t13, Math.fma(-_t0, _t1, 1.0), _t13 < 0.5 ? 1.0 : 0.0, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_sf769acb1_2(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77) {
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
        dest.putDouble(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.putDouble(destOffset + 8, Math.fma(_t73, _t98, _t74 * _t99));
        dest.putDouble(destOffset + 16, Math.fma(_t70, _t98, _t71 * _t99));
        dest.putDouble(destOffset + 24, Math.fma(_t68, _t98, _t69 * _t99));
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
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        return squad_api_se3b6c06b_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, _t7, _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, 1.0 - _t7, _t0 * _t1, Math.fma(-_t0, _t1, 1.0));
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_se3b6c06b_1(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        return squad_api_se3b6c06b_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, _t12, _t13, _t14, _t13 < 0.5 ? 1.0 : 0.0, java.lang.Math.min(4.0, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_se3b6c06b_2(java.nio.ByteBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t7, double _t12, double _t13, double _t14, double _t17, double _t25, double _t26) {
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
        return squad_api_se3b6c06b_3(dest, destOffset, _t13, _t14, _t17, Math.fma(_control0x, _t57, _control1x * _t55), Math.fma(_control0w, _t57, _control1w * _t55), Math.fma(_selfw, _t58, _targetw * _t56), Math.fma(_control0z, _t57, _control1z * _t55), Math.fma(_selfz, _t58, _targetz * _t56), Math.fma(_selfx, _t58, _targetx * _t56), Math.fma(_control0y, _t57, _control1y * _t55), Math.fma(_selfy, _t58, _targety * _t56));
    }

    /** Piece 4 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_se3b6c06b_3(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
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
        dest.putDouble(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.putDouble(destOffset + 8, Math.fma(_t73, _t98, _t74 * _t99));
        dest.putDouble(destOffset + 16, Math.fma(_t70, _t98, _t71 * _t99));
        dest.putDouble(destOffset + 24, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putDouble(destOffset + 8, Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx)));
        dest.putDouble(destOffset + 16, Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.putDouble(destOffset + 24, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putDouble(destOffset + 8, Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx)));
        dest.putDouble(destOffset + 16, Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.putDouble(destOffset + 24, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.putDouble(destOffset + 8, Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz)));
        dest.putDouble(destOffset + 16, Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.putDouble(destOffset + 24, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.putDouble(destOffset + 8, Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz)));
        dest.putDouble(destOffset + 16, Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.putDouble(destOffset + 24, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(weight, otherX, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(weight, otherY, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(weight, otherZ, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(weight, otherW, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset, Math.fma(weight, _otherx, _selfx));
        dest.putDouble(destOffset + 8, Math.fma(weight, _othery, _selfy));
        dest.putDouble(destOffset + 16, Math.fma(weight, _otherz, _selfz));
        dest.putDouble(destOffset + 24, Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    public static double angle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static double angle_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        return 2.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), src.getDouble(srcOffset + 24));
    }

    public static double angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleTo_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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

    public static double angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static double angleTo_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
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

    public static java.nio.ByteBuffer axis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer axis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
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

    public static java.nio.ByteBuffer calculateW_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer calculateW_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        dest.putDouble(destOffset, _selfx);
        dest.putDouble(destOffset + 8, _selfy);
        dest.putDouble(destOffset + 16, _selfz);
        dest.putDouble(destOffset + 24, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0))))));
        return dest;
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        double _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        double _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        double _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        dest.putDouble(destOffset, Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)));
        dest.putDouble(destOffset + 8, Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)));
        dest.putDouble(destOffset + 16, Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)));
        dest.putDouble(destOffset + 24, Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _qx = q.getDouble(qOffset);
        double _qy = q.getDouble(qOffset + 8);
        double _qz = q.getDouble(qOffset + 16);
        double _qw = q.getDouble(qOffset + 24);
        double _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        double _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        double _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        double _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        dest.putDouble(destOffset, Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23)));
        dest.putDouble(destOffset + 8, Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20)));
        return conjugateBy_api_sbd57f0c1_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_sbd57f0c1_1(java.nio.ByteBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        dest.putDouble(destOffset + 16, Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23)));
        dest.putDouble(destOffset + 24, Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.putDouble(destOffset + 16, (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv);
        dest.putDouble(destOffset + 24, Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _otherx = other.getDouble(otherOffset);
        double _othery = other.getDouble(otherOffset + 8);
        double _otherz = other.getDouble(otherOffset + 16);
        double _otherw = other.getDouble(otherOffset + 24);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.putDouble(destOffset, (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.putDouble(destOffset + 8, -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.putDouble(destOffset + 16, (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv);
        dest.putDouble(destOffset + 24, Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1));
        return dest;
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, src.getDouble(srcOffset + 24), Math.fma(otherZ, src.getDouble(srcOffset + 16), Math.fma(otherX, src.getDouble(srcOffset), otherY * src.getDouble(srcOffset + 8))));
    }

    public static double dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getDouble(otherOffset + 24), src.getDouble(srcOffset + 24), Math.fma(other.getDouble(otherOffset + 16), src.getDouble(srcOffset + 16), Math.fma(other.getDouble(otherOffset), src.getDouble(srcOffset), other.getDouble(otherOffset + 8) * src.getDouble(srcOffset + 8))));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _t2 = java.lang.Math.min(Math.exp(src.getDouble(srcOffset + 24)), 1.7976931348623157E308);
        double _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t7 = Math.sin(_t5);
        double _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0) {
            dest.putDouble(destOffset, _selfx * _t10);
            dest.putDouble(destOffset + 8, _selfy * _t10);
            dest.putDouble(destOffset + 16, _selfz * _t10);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t7, _t5) * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfy * _selfz;
        double _t3 = _selfz * _selfz;
        double _t8 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            dest.putDouble(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t3), 1.0)));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t9, _t10));
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t3), 1.0)));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t8, java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfy * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfw, _t1);
        double _t8 = 2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t9 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dest.putDouble(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.putDouble(destOffset + 8, 0.0);
        } else {
            dest.putDouble(destOffset, Math.atan2(_t7, _t9));
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0)));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = _selfz * _selfz;
        double _t8 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, -(_selfy * _selfz));
        double _t10 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t3), 1.0)));
            dest.putDouble(destOffset + 16, 0.0);
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(_t8, _t10));
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t3), 1.0)));
        }
        dest.putDouble(destOffset, Math.atan2(_t9, java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t8 = 2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t9 = Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
        } else {
            dest.putDouble(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0)));
            dest.putDouble(destOffset + 8, Math.atan2(_t8, _t9));
        }
        dest.putDouble(destOffset + 16, Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t1 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t8 = 2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t9 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t1), 1.0)));
        } else {
            dest.putDouble(destOffset + 8, Math.atan2(2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.putDouble(destOffset + 16, Math.atan2(_t8, _t9));
        }
        dest.putDouble(destOffset, Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t8 = 2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t9 = Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 16, Math.atan2(2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0)));
        } else {
            dest.putDouble(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.putDouble(destOffset + 16, Math.atan2(_t7, _t9));
        }
        dest.putDouble(destOffset + 8, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * dt;
        double _t1 = angularVel.getDouble(angularVelOffset + 16) * _t0;
        double _t2 = angularVel.getDouble(angularVelOffset) * _t0;
        double _t3 = angularVel.getDouble(angularVelOffset + 8) * _t0;
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        return integrate_api_s2aeb5060_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer integrate_api_s2aeb5060_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t10, double _t15, double _t16, double _t17) {
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
        dest.putDouble(destOffset + 8, -(2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.putDouble(destOffset + 16, -(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.putDouble(destOffset + 8, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
        dest.putDouble(destOffset + 16, -(2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -(2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        dest.putDouble(destOffset + 8, -(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        dest.putDouble(destOffset + 16, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.putDouble(destOffset + 8, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putDouble(destOffset + 16, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static double lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return DoubleQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
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
        dest.putDouble(destOffset + 24, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
        dest.putDouble(destOffset + 8, -(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.putDouble(destOffset + 16, -(2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -(2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.putDouble(destOffset + 8, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
        dest.putDouble(destOffset + 16, -(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, -(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        dest.putDouble(destOffset + 8, -(2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        dest.putDouble(destOffset + 16, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.putDouble(destOffset + 8, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
        dest.putDouble(destOffset + 16, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest.putDouble(destOffset + 8, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putDouble(destOffset + 16, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        return pow_api_sbcf5d9f9_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t28, _t12 * (_t28 / _t26));
    }

    /** Piece 2 of {@code pow_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer pow_api_sbcf5d9f9_1(java.nio.ByteBuffer dest, int destOffset, double _t12, double _t20, double _t21, double _t22, double _t25, double _t26, double _t28, double _t31) {
        if (_t25 > 0.0) {
            dest.putDouble(destOffset, _t21 * _t31);
            dest.putDouble(destOffset + 8, _t22 * _t31);
            dest.putDouble(destOffset + 16, _t20 * _t31);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
        }
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t28, _t26) * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        return rotateTowards_api_saeaa522a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0);
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_saeaa522a_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39) {
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
            dest.putDouble(destOffset, _t73 * _t67);
            dest.putDouble(destOffset + 8, _t73 * _t68);
            dest.putDouble(destOffset + 16, _t73 * _t66);
            dest.putDouble(destOffset + 24, _t73 * _t65);
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _targetx = target.getDouble(targetOffset);
        double _targety = target.getDouble(targetOffset + 8);
        double _targetz = target.getDouble(targetOffset + 16);
        double _targetw = target.getDouble(targetOffset + 24);
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
        return rotateTowards_api_sa8e4c2f4_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_sa8e4c2f4_1(java.nio.ByteBuffer dest, int destOffset, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
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
        return rotateTowards_api_sa8e4c2f4_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_sa8e4c2f4_2(java.nio.ByteBuffer dest, int destOffset, double _t65, double _t66, double _t67, double _t68, double _t72, double _t73) {
        if (_t72 != 0.0) {
            dest.putDouble(destOffset, _t73 * _t67);
            dest.putDouble(destOffset + 8, _t73 * _t68);
            dest.putDouble(destOffset + 16, _t73 * _t66);
            dest.putDouble(destOffset + 24, _t73 * _t65);
        } else {
            dest.putDouble(destOffset, 0.0);
            dest.putDouble(destOffset + 8, 0.0);
            dest.putDouble(destOffset + 16, 0.0);
            dest.putDouble(destOffset + 24, 0.0);
        }
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        return lookAlong_api_sf826c81c_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0 / java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_1(java.nio.ByteBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31, double _t34, double _t35) {
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
        return lookAlong_api_sf826c81c_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t68, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp1, double _sp2) {
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        return lookAlong_api_sf826c81c_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sf826c81c_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t120, double _t121, double _t122, double _t123) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
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
        return lookAlong_api_s2f25056e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t20, _t13, -(_t21 * _t12)), Math.fma(_t21, _t11, -(_t22 * _t13)), Math.fma(_t22, _t12, -(_t20 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31) {
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
        return lookAlong_api_s2f25056e_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_2(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t68, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75) {
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        return lookAlong_api_s2f25056e_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_3(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t120, double _t121, double _t122, double _t123) {
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, axisX * _t1);
        dest.putDouble(destOffset + 8, axisY * _t1);
        dest.putDouble(destOffset + 16, axisZ * _t1);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t1, _t0));
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
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, _axisx * _t1);
        dest.putDouble(destOffset + 8, _axisy * _t1);
        dest.putDouble(destOffset + 16, _axisz * _t1);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return makeRotationLookAlong_api_scb8583d0_1(dest, destOffset, _t11, _t12, _t13, _t39, _t40, _t41, -_t40, -_t39, 1.0 + _t39, _t40 + _t12, _t12 - _t40);
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_scb8583d0_1(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t39, double _t40, double _t41, double _t42, double _t43, double _t45, double _t49, double _t50) {
        double _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        double _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        double _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11));
        double _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39));
        return makeRotationLookAlong_api_scb8583d0_2(dest, destOffset, _t11, _t12, _t39, _t42, _t49, _t50, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, _t73, _t74, _t75, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_scb8583d0_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t67, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t50);
            dest.putDouble(destOffset + 16, _sp0 * _t70);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t73));
                dest.putDouble(destOffset + 8, _sp3 * _t69);
                dest.putDouble(destOffset + 16, _sp3 * _t49);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t69);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t50);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t49);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 24, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        double _dirx = dir.getDouble(dirOffset);
        double _diry = dir.getDouble(dirOffset + 8);
        double _dirz = dir.getDouble(dirOffset + 16);
        double _upx = up.getDouble(upOffset);
        double _upy = up.getDouble(upOffset + 8);
        double _upz = up.getDouble(upOffset + 16);
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
        return makeRotationLookAlong_api_s64af833a_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0 / java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_1(java.nio.ByteBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31, double _t34, double _t35) {
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
        return makeRotationLookAlong_api_s64af833a_2(dest, destOffset, _t11, _t12, _t39, _t42, _t40 + _t12, _t12 - _t40, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_2(java.nio.ByteBuffer dest, int destOffset, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t67, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            dest.putDouble(destOffset, _sp0 * _t67);
            dest.putDouble(destOffset + 8, _sp0 * _t50);
            dest.putDouble(destOffset + 16, _sp0 * _t70);
            dest.putDouble(destOffset + 24, 0.5 * java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.putDouble(destOffset, 0.5 * java.lang.Math.sqrt(_t73));
                dest.putDouble(destOffset + 8, _sp3 * _t69);
                dest.putDouble(destOffset + 16, _sp3 * _t49);
                dest.putDouble(destOffset + 24, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.putDouble(destOffset, _sp1 * _t69);
                    dest.putDouble(destOffset + 8, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.putDouble(destOffset + 16, _sp1 * _t66);
                    dest.putDouble(destOffset + 24, _sp1 * _t50);
                } else {
                    dest.putDouble(destOffset, _sp2 * _t49);
                    dest.putDouble(destOffset + 8, _sp2 * _t66);
                    dest.putDouble(destOffset + 16, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.putDouble(destOffset + 24, _sp2 * _t70);
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
        return makeRotationTo_api_s5764ce82_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_s5764ce82_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
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
        double _t15 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        double _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_s295abccd_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_s295abccd_1(java.nio.ByteBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
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

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, _t1);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
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
        dest.putDouble(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.putDouble(destOffset + 8, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_t9, _t7, _t12 * _t5));
        dest.putDouble(destOffset + 24, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
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
        dest.putDouble(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.putDouble(destOffset + 16, Math.fma(_t10, _t5, _t11 * _t7));
        dest.putDouble(destOffset + 24, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, _t1);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
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
        dest.putDouble(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.putDouble(destOffset + 8, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.putDouble(destOffset + 16, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.putDouble(destOffset + 24, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
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
        dest.putDouble(destOffset, Math.fma(_t9, _t6, _t12 * _t5));
        dest.putDouble(destOffset + 8, Math.fma(_t10, _t6, _t11 * _t5));
        dest.putDouble(destOffset + 16, Math.fma(_t11, _t6, -(_t10 * _t5)));
        dest.putDouble(destOffset + 24, Math.fma(_t12, _t6, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.putDouble(destOffset, 0.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, _t1);
        dest.putDouble(destOffset + 24, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
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
        dest.putDouble(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.putDouble(destOffset + 8, Math.fma(_t9, _t7, _t12 * _t5));
        dest.putDouble(destOffset + 16, Math.fma(_t10, _t5, _t11 * _t7));
        dest.putDouble(destOffset + 24, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        DoubleQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
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
        dest.putDouble(destOffset, Math.fma(_t12, _t5, -(_t9 * _t8)));
        dest.putDouble(destOffset + 8, Math.fma(_t10, _t8, _t11 * _t5));
        dest.putDouble(destOffset + 16, Math.fma(_t11, _t8, -(_t10 * _t5)));
        dest.putDouble(destOffset + 24, Math.fma(_t9, _t5, _t12 * _t8));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t2, -(_selfz * _t1)));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t1, _selfz * _t2));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, _selfz * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t2, -(_selfx * _t1)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, -(_selfy * _t1)));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t1, _selfy * _t2));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axis.getDouble(axisOffset) * _t1;
        double _t3 = axis.getDouble(axisOffset + 16) * _t1;
        double _t4 = axis.getDouble(axisOffset + 8) * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
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
        double _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        double _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_s9441c89f_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_s9441c89f_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_s842c5910_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_s842c5910_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t2, _selfz * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t2, -(_selfy * _t1)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXYZ_api_sf844a819_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_sf844a819_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXZY_api_s2462d199_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_s2462d199_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, -(_selfz * _t1)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t1, _selfz * _t2));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21)));
        return rotateYXZ_api_sbfc926d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_sbfc926d_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateYZX_api_s42d1eb59_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_s42d1eb59_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.putDouble(destOffset, Math.fma(_selfx, _t2, _selfy * _t1));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t2, -(_selfx * _t1)));
        dest.putDouble(destOffset + 16, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19)));
        return rotateZXY_api_s50246ed_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_s50246ed_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
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
        dest.putDouble(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putDouble(destOffset + 8, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateZYX_api_s7fbcba89_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_s7fbcba89_1(java.nio.ByteBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putDouble(destOffset + 24, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, vY, -(_selfy * vX));
        double _t10 = 2.0 * Math.fma(_selfz, vX, -(_selfx * vZ));
        double _t11 = 2.0 * Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.putDouble(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX))));
        dest.putDouble(destOffset + 8, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY))));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ))));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _t9 = 2.0 * Math.fma(_selfx, _vy, -(_selfy * _vx));
        double _t10 = 2.0 * Math.fma(_selfz, _vx, -(_selfx * _vz));
        double _t11 = 2.0 * Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.putDouble(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.putDouble(destOffset + 8, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy))));
        dest.putDouble(destOffset + 16, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        DoubleQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _t9 = 2.0 * Math.fma(_selfx, vZ, -(_selfz * vX));
        double _t10 = 2.0 * Math.fma(_selfy, vX, -(_selfx * vY));
        double _t11 = 2.0 * Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.putDouble(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX))));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY))));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ))));
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
        double _selfx = src.getDouble(srcOffset);
        double _selfy = src.getDouble(srcOffset + 8);
        double _selfz = src.getDouble(srcOffset + 16);
        double _selfw = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _vz = v.getDouble(vOffset + 16);
        double _t9 = 2.0 * Math.fma(_selfx, _vz, -(_selfz * _vx));
        double _t10 = 2.0 * Math.fma(_selfy, _vx, -(_selfx * _vy));
        double _t11 = 2.0 * Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.putDouble(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.putDouble(destOffset + 8, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy))));
        dest.putDouble(destOffset + 16, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz))));
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
