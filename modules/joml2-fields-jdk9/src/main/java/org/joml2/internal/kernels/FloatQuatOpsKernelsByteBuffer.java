// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code FloatQuatOps} and its sibling kernel units. Not public API.
 */
public final class FloatQuatOpsKernelsByteBuffer {
    private FloatQuatOpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest.putFloat(destOffset, -(_selfx * _t3_inv));
        dest.putFloat(destOffset + 4, -(_selfy * _t3_inv));
        dest.putFloat(destOffset + 8, -(_selfz * _t3_inv));
        dest.putFloat(destOffset + 12, _selfw * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        float _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putFloat(destOffset, -(_t20 * _t27_inv));
        dest.putFloat(destOffset + 4, -(_t23 * _t27_inv));
        dest.putFloat(destOffset + 8, -(_t22 * _t27_inv));
        dest.putFloat(destOffset + 12, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        float _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putFloat(destOffset, -(_t20 * _t27_inv));
        dest.putFloat(destOffset + 4, -(_t23 * _t27_inv));
        dest.putFloat(destOffset + 8, -(_t22 * _t27_inv));
        dest.putFloat(destOffset + 12, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
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
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
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

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
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
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
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
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.nio.ByteBuffer makeUniformRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, float u1, float u2, float u3) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeUniformRotation_unsafe(_destBase, u1, u2, u3);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformRotation_api(java.nio.ByteBuffer dest, int destOffset, float u1, float u2, float u3) {
        float _t0 = (float) java.lang.Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u1);
        float _t6 = Math.sin(_t3);
        dest.putFloat(destOffset, _t4 * _t5);
        dest.putFloat(destOffset + 4, Math.cosFromSin(_t4, _t1) * _t5);
        dest.putFloat(destOffset + 8, _t6 * _t0);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t6, _t3) * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
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
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, _vBase);
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

    public static java.nio.ByteBuffer makeFromDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromDualQuat_api(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        dest.putFloat(destOffset, dqRX);
        dest.putFloat(destOffset + 4, dqRY);
        dest.putFloat(destOffset + 8, dqRZ);
        dest.putFloat(destOffset + 12, dqRW);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m20 = m.getFloat(mOffset + 8);
        float _m01 = m.getFloat(mOffset + 12);
        float _m11 = m.getFloat(mOffset + 16);
        float _m21 = m.getFloat(mOffset + 20);
        float _m02 = m.getFloat(mOffset + 24);
        float _m12 = m.getFloat(mOffset + 28);
        float _m22 = m.getFloat(mOffset + 32);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x3_api_s18f08509_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat3x3_api_s18f08509_1(java.nio.ByteBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t1);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t4);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putFloat(destOffset, _sp1 * _t4);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m01 = m.getFloat(mOffset + 4);
        float _m02 = m.getFloat(mOffset + 8);
        float _m10 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m12 = m.getFloat(mOffset + 24);
        float _m20 = m.getFloat(mOffset + 32);
        float _m21 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x4_api_s725968d6_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat3x4_api_s725968d6_1(java.nio.ByteBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t1);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t4);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putFloat(destOffset, _sp1 * _t4);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeFromMatrixMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m20 = m.getFloat(mOffset + 8);
        float _m01 = m.getFloat(mOffset + 16);
        float _m11 = m.getFloat(mOffset + 20);
        float _m21 = m.getFloat(mOffset + 24);
        float _m02 = m.getFloat(mOffset + 32);
        float _m12 = m.getFloat(mOffset + 36);
        float _m22 = m.getFloat(mOffset + 40);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat4x4_api_sbc07e171_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeFromMatrixMat4x4_api_sbc07e171_1(java.nio.ByteBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t1);
            dest.putFloat(destOffset + 4, _sp0 * _t7);
            dest.putFloat(destOffset + 8, _sp0 * _t9);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.putFloat(destOffset + 4, _sp3 * _t4);
                dest.putFloat(destOffset + 8, _sp3 * _t6);
                dest.putFloat(destOffset + 12, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.putFloat(destOffset, _sp1 * _t4);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.putFloat(destOffset + 8, _sp1 * _t8);
                    dest.putFloat(destOffset + 12, _sp1 * _t7);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t6);
                    dest.putFloat(destOffset + 4, _sp2 * _t8);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.putFloat(destOffset + 12, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toDualQuat_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, _selfz);
        dest.putFloat(destOffset + 12, _selfw);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return toMatrix_api_s9b3d2c84_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_s9b3d2c84_1(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 44, 0.0f);
        dest.putFloat(destOffset + 48, 0.0f);
        dest.putFloat(destOffset + 52, 0.0f);
        dest.putFloat(destOffset + 56, 0.0f);
        dest.putFloat(destOffset + 60, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 12, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 16, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 20, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 28, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 32, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 20, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 36, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 40, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_unsafe(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_api(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        swing.putFloat(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.putFloat(swingOffset + 4, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        swing.putFloat(swingOffset + 8, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        return decomposeSwingTwist_api_s91dac6f6_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_s91dac6f6_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 12, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.putFloat(twistOffset, _t12);
        twist.putFloat(twistOffset + 4, _t13);
        twist.putFloat(twistOffset + 8, _t14);
        twist.putFloat(twistOffset + 12, _t11);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_unsafe(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, _axisBase);
        return swing;
    }

    public static java.nio.ByteBuffer decomposeSwingTwist_api(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        swing.putFloat(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.putFloat(swingOffset + 4, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return decomposeSwingTwist_api_s5feaaa58_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_s5feaaa58_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 8, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        swing.putFloat(swingOffset + 12, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.putFloat(twistOffset, _t12);
        twist.putFloat(twistOffset + 4, _t13);
        twist.putFloat(twistOffset + 8, _t14);
        twist.putFloat(twistOffset + 12, _t11);
        return swing;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        FloatQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.putFloat(destOffset + 12, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        FloatQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return getSwing_api_s32840fb0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_s32840fb0_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.putFloat(destOffset + 12, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        FloatQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = Math.fma(axisZ, src.getFloat(srcOffset + 8), Math.fma(axisX, src.getFloat(srcOffset), axisY * src.getFloat(srcOffset + 4)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.putFloat(destOffset, axisX * _t6);
            dest.putFloat(destOffset + 4, axisY * _t6);
            dest.putFloat(destOffset + 8, axisZ * _t6);
            dest.putFloat(destOffset + 12, _selfw * _t5);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 1.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        FloatQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _selfw = src.getFloat(srcOffset + 12);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t2 = Math.fma(_axisz, src.getFloat(srcOffset + 8), Math.fma(_axisx, src.getFloat(srcOffset), _axisy * src.getFloat(srcOffset + 4)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.putFloat(destOffset, _axisx * _t6);
            dest.putFloat(destOffset + 4, _axisy * _t6);
            dest.putFloat(destOffset + 8, _axisz * _t6);
            dest.putFloat(destOffset + 12, _selfw * _t5);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 1.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, otherY - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(t, otherZ - _selfz, _selfz));
        dest.putFloat(destOffset + 12, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, _othery - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(t, _otherz - _selfz, _selfz));
        dest.putFloat(destOffset + 12, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        float _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        float _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        float _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dest.putFloat(destOffset, _t6 * _t12);
            dest.putFloat(destOffset + 4, _t7 * _t12);
            dest.putFloat(destOffset + 8, _t5 * _t12);
            dest.putFloat(destOffset + 12, _t4 * _t12);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = Math.fma(alpha, target.getFloat(targetOffset + 12) - _selfw, _selfw);
        float _t5 = Math.fma(alpha, target.getFloat(targetOffset + 8) - _selfz, _selfz);
        float _t6 = Math.fma(alpha, target.getFloat(targetOffset) - _selfx, _selfx);
        float _t7 = Math.fma(alpha, target.getFloat(targetOffset + 4) - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dest.putFloat(destOffset, _t6 * _t12);
            dest.putFloat(destOffset + 4, _t7 * _t12);
            dest.putFloat(destOffset + 8, _t5 * _t12);
            dest.putFloat(destOffset + 12, _t4 * _t12);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
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
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_api_s272ba3ed_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_s272ba3ed_1(java.nio.ByteBuffer dest, int destOffset, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            dest.putFloat(destOffset, _t19 * _t25);
            dest.putFloat(destOffset + 4, _t20 * _t25);
            dest.putFloat(destOffset + 8, _t18 * _t25);
            dest.putFloat(destOffset + 12, _t17 * _t25);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
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
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_api_sfe212643_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_sfe212643_1(java.nio.ByteBuffer dest, int destOffset, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            dest.putFloat(destOffset, _t19 * _t25);
            dest.putFloat(destOffset + 4, _t20 * _t25);
            dest.putFloat(destOffset + 8, _t18 * _t25);
            dest.putFloat(destOffset + 12, _t17 * _t25);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 1.0f - alpha;
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        dest.putFloat(destOffset, Math.fma(_selfx, _t27, targetX * _t26));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t27, targetY * _t26));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t27, targetZ * _t26));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t27, targetW * _t26));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t0 = 1.0f - alpha;
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        dest.putFloat(destOffset, Math.fma(_selfx, _t27, _targetx * _t26));
        return slerp_api_s4ecdee4e_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t26, _t27);
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_s4ecdee4e_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _targety, float _targetz, float _targetw, float _t26, float _t27) {
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t27, _targety * _t26));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t27, _targetz * _t26));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t27, _targetw * _t26));
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        return slerpShortest_api_sa7dc0de6_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_sa7dc0de6_1(java.nio.ByteBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dest.putFloat(destOffset, _t50 * _t44);
            dest.putFloat(destOffset + 4, _t50 * _t45);
            dest.putFloat(destOffset + 8, _t50 * _t43);
            dest.putFloat(destOffset + 12, _t50 * _t42);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        return slerpShortest_api_s911c8d9c_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_s911c8d9c_1(java.nio.ByteBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dest.putFloat(destOffset, _t50 * _t44);
            dest.putFloat(destOffset + 4, _t50 * _t45);
            dest.putFloat(destOffset + 8, _t50 * _t43);
            dest.putFloat(destOffset + 12, _t50 * _t42);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer squad_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.ByteBuffer squad_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t12 = 1.0f - _t7;
        float _t25 = java.lang.Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t27 = quatArcAngle(_t25);
        float _t29 = 4.0f - _t25;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t45 = 2.0f / _t41;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = t + t;
        return squad_api_sc59eb0fe_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, _t1, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t0 * _t1);
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_sc59eb0fe_1(java.nio.ByteBuffer dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t12, float _t55, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        float _t26 = java.lang.Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t28 = quatArcAngle(_t26);
        float _t30 = 4.0f - _t26;
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t46 = 2.0f / _t43;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        float _t67 = Math.fma(control0X, _t57, control1X * _t55);
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(_selfw, _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(_selfz, _t58, targetZ * _t56);
        float _t72 = Math.fma(_selfx, _t58, targetX * _t56);
        return squad_api_sc59eb0fe_2(dest, destOffset, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_sc59eb0fe_2(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77) {
        float _t78 = _t73 + _t74;
        float _t83 = java.lang.Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dest.putFloat(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.putFloat(destOffset + 4, Math.fma(_t73, _t98, _t74 * _t99));
        dest.putFloat(destOffset + 8, Math.fma(_t70, _t98, _t71 * _t99));
        dest.putFloat(destOffset + 12, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.nio.ByteBuffer squad_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _control0Base = UnsafeOpsHolder.U.getLong(control0, UnsafeCopy.BB_ADDRESS_OFFSET) + control0Offset;
        long _control1Base = UnsafeOpsHolder.U.getLong(control1, UnsafeCopy.BB_ADDRESS_OFFSET) + control1Offset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, _control0Base, _control1Base, _targetBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer squad_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _control0x = control0.getFloat(control0Offset);
        float _control0y = control0.getFloat(control0Offset + 4);
        float _control0z = control0.getFloat(control0Offset + 8);
        float _control0w = control0.getFloat(control0Offset + 12);
        float _control1x = control1.getFloat(control1Offset);
        float _control1y = control1.getFloat(control1Offset + 4);
        float _control1z = control1.getFloat(control1Offset + 8);
        float _control1w = control1.getFloat(control1Offset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        return squad_api_s365cca1c_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, _t7, _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, 1.0f - _t7, _t0 * _t1, Math.fma(-_t0, _t1, 1.0f));
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_s365cca1c_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        return squad_api_s365cca1c_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, _t12, _t13, _t14, _t13 < 0.5f ? 1.0f : 0.0f, java.lang.Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_s365cca1c_2(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        return squad_api_s365cca1c_3(dest, destOffset, _t13, _t14, _t17, Math.fma(_control0x, _t57, _control1x * _t55), Math.fma(_control0w, _t57, _control1w * _t55), Math.fma(_selfw, _t58, _targetw * _t56), Math.fma(_control0z, _t57, _control1z * _t55), Math.fma(_selfz, _t58, _targetz * _t56), Math.fma(_selfx, _t58, _targetx * _t56), Math.fma(_control0y, _t57, _control1y * _t55), Math.fma(_selfy, _t58, _targety * _t56));
    }

    /** Piece 4 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_s365cca1c_3(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = java.lang.Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dest.putFloat(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.putFloat(destOffset + 4, Math.fma(_t73, _t98, _t74 * _t99));
        dest.putFloat(destOffset + 8, Math.fma(_t70, _t98, _t71 * _t99));
        dest.putFloat(destOffset + 12, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putFloat(destOffset + 4, Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx)));
        dest.putFloat(destOffset + 8, Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.putFloat(destOffset + 12, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
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
        dest.putFloat(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putFloat(destOffset + 4, Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx)));
        dest.putFloat(destOffset + 8, Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.putFloat(destOffset + 12, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.putFloat(destOffset + 4, Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz)));
        dest.putFloat(destOffset + 8, Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.putFloat(destOffset + 12, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.putFloat(destOffset + 4, Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz)));
        dest.putFloat(destOffset + 8, Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.putFloat(destOffset + 12, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(weight, otherX, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(weight, otherY, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(weight, otherZ, _selfz));
        dest.putFloat(destOffset + 12, Math.fma(weight, otherW, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(weight, _otherx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(weight, _othery, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(weight, _otherz, _selfz));
        dest.putFloat(destOffset + 12, Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    public static float angle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static float angle_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        return 2.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), src.getFloat(srcOffset + 12));
    }

    public static float angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0f) {
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
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static float angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static float angleTo_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0f) {
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
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static java.nio.ByteBuffer axis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer axis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
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

    public static java.nio.ByteBuffer calculateW_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer calculateW_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, _selfz);
        dest.putFloat(destOffset + 12, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        return dest;
    }

    public static java.nio.ByteBuffer conjugate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer conjugate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -_selfx);
        dest.putFloat(destOffset + 4, -_selfy);
        dest.putFloat(destOffset + 8, -_selfz);
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        float _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        dest.putFloat(destOffset, Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)));
        dest.putFloat(destOffset + 4, Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)));
        dest.putFloat(destOffset + 8, Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)));
        dest.putFloat(destOffset + 12, Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.ByteBuffer conjugateBy_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _qx = q.getFloat(qOffset);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        float _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        dest.putFloat(destOffset, Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23)));
        dest.putFloat(destOffset + 4, Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20)));
        return conjugateBy_api_sbd57f0c1_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_sbd57f0c1_1(java.nio.ByteBuffer dest, int destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.putFloat(destOffset + 8, Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23)));
        dest.putFloat(destOffset + 12, Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.putFloat(destOffset + 8, (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv);
        dest.putFloat(destOffset + 12, Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.putFloat(destOffset + 8, (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv);
        dest.putFloat(destOffset + 12, Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, src.getFloat(srcOffset + 12), Math.fma(otherZ, src.getFloat(srcOffset + 8), Math.fma(otherX, src.getFloat(srcOffset), otherY * src.getFloat(srcOffset + 4))));
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getFloat(otherOffset + 12), src.getFloat(srcOffset + 12), Math.fma(other.getFloat(otherOffset + 8), src.getFloat(srcOffset + 8), Math.fma(other.getFloat(otherOffset), src.getFloat(srcOffset), other.getFloat(otherOffset + 4) * src.getFloat(srcOffset + 4))));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = java.lang.Math.min(Math.exp(src.getFloat(srcOffset + 12)), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t7 = Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            dest.putFloat(destOffset, _selfx * _t10);
            dest.putFloat(destOffset + 4, _selfy * _t10);
            dest.putFloat(destOffset + 8, _selfz * _t10);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t7, _t5) * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dest.putFloat(destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t9, _t10));
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfy * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _t1);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest.putFloat(destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t7, _t9));
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f)));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz));
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_t8, _t10));
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
        }
        dest.putFloat(destOffset, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
        } else {
            dest.putFloat(destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
            dest.putFloat(destOffset + 4, Math.atan2(_t8, _t9));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t1), 1.0f)));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.putFloat(destOffset + 8, Math.atan2(_t8, _t9));
        }
        dest.putFloat(destOffset, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
        } else {
            dest.putFloat(destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.putFloat(destOffset + 8, Math.atan2(_t7, _t9));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t10 = Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 * (1.0f / (float) java.lang.Math.sqrt(_t6));
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, float dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _angularVelBase = UnsafeOpsHolder.U.getLong(angularVel, UnsafeCopy.BB_ADDRESS_OFFSET) + angularVelOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, _angularVelBase, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, float dt) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.getFloat(angularVelOffset + 8) * _t0;
        float _t2 = angularVel.getFloat(angularVelOffset) * _t0;
        float _t3 = angularVel.getFloat(angularVelOffset + 4) * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        return integrate_api_see2cdcaf_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer integrate_api_see2cdcaf_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t15, float _t16, float _t17) {
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t12 * _t16));
            dest.putFloat(destOffset + 4, -(_t10 * _t16));
            dest.putFloat(destOffset + 8, -(_t9 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t9 * _t16));
            dest.putFloat(destOffset + 4, -(_t12 * _t16));
            dest.putFloat(destOffset + 8, -(_t10 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t10 * _t16));
            dest.putFloat(destOffset + 4, -(_t9 * _t16));
            dest.putFloat(destOffset + 8, -(_t12 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.putFloat(destOffset + 4, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.putFloat(destOffset + 8, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.putFloat(destOffset + 4, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.putFloat(destOffset + 8, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        dest.putFloat(destOffset + 4, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        dest.putFloat(destOffset + 8, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 8, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t12 * _t16);
            dest.putFloat(destOffset + 4, _t10 * _t16);
            dest.putFloat(destOffset + 8, _t9 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t9 * _t16);
            dest.putFloat(destOffset + 4, _t12 * _t16);
            dest.putFloat(destOffset + 8, _t10 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t10 * _t16);
            dest.putFloat(destOffset + 4, _t9 * _t16);
            dest.putFloat(destOffset + 8, _t12 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static float length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        return (float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static float lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.putFloat(destOffset, _selfx * _t6);
            dest.putFloat(destOffset + 4, _selfy * _t6);
            dest.putFloat(destOffset + 8, _selfz * _t6);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t12 * _t16));
            dest.putFloat(destOffset + 4, -(_t9 * _t16));
            dest.putFloat(destOffset + 8, -(_t10 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t10 * _t16));
            dest.putFloat(destOffset + 4, -(_t12 * _t16));
            dest.putFloat(destOffset + 8, -(_t9 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, -(_t9 * _t16));
            dest.putFloat(destOffset + 4, -(_t10 * _t16));
            dest.putFloat(destOffset + 8, -(_t12 * _t16));
        } else {
            dest.putFloat(destOffset, -0.0f);
            dest.putFloat(destOffset + 4, -0.0f);
            dest.putFloat(destOffset + 8, -0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
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

    public static java.nio.ByteBuffer normalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.putFloat(destOffset + 4, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.putFloat(destOffset + 8, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.putFloat(destOffset + 4, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.putFloat(destOffset + 8, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        dest.putFloat(destOffset + 4, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        dest.putFloat(destOffset + 8, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        dest.putFloat(destOffset + 8, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest.putFloat(destOffset + 4, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 8, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t12 * _t16);
            dest.putFloat(destOffset + 4, _t9 * _t16);
            dest.putFloat(destOffset + 8, _t10 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t10 * _t16);
            dest.putFloat(destOffset + 4, _t12 * _t16);
            dest.putFloat(destOffset + 8, _t9 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.putFloat(destOffset, _t9 * _t16);
            dest.putFloat(destOffset + 4, _t10 * _t16);
            dest.putFloat(destOffset + 8, _t12 * _t16);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.pow_unsafe(_destBase, _srcBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t12 = java.lang.Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))), 3.4028235E38f);
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
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        return pow_api_sc5395ae_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t28, _t12 * (_t28 / _t26));
    }

    /** Piece 2 of {@code pow_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer pow_api_sc5395ae_1(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t28, float _t31) {
        if (_t25 > 0.0f) {
            dest.putFloat(destOffset, _t21 * _t31);
            dest.putFloat(destOffset + 4, _t22 * _t31);
            dest.putFloat(destOffset + 8, _t20 * _t31);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t28, _t26) * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t7 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t11 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        return rotateTowards_api_s860c0395_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_s860c0395_1(java.nio.ByteBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t36) {
        float _t39 = _t36 > 0.0f ? java.lang.Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = Math.sin(_t11 * _t39);
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return rotateTowards_api_s860c0395_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_s860c0395_2(java.nio.ByteBuffer dest, int destOffset, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            dest.putFloat(destOffset, _t73 * _t67);
            dest.putFloat(destOffset + 4, _t73 * _t68);
            dest.putFloat(destOffset + 8, _t73 * _t66);
            dest.putFloat(destOffset + 12, _t73 * _t65);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _targetBase, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t11 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
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
        return rotateTowards_api_s749e78b_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_s749e78b_1(java.nio.ByteBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? java.lang.Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = Math.sin(_t11 * _t39);
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return rotateTowards_api_s749e78b_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_s749e78b_2(java.nio.ByteBuffer dest, int destOffset, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            dest.putFloat(destOffset, _t73 * _t67);
            dest.putFloat(destOffset + 4, _t73 * _t68);
            dest.putFloat(destOffset + 8, _t73 * _t66);
            dest.putFloat(destOffset + 12, _t73 * _t65);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return lookAlong_api_sa2b62cbc_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sa2b62cbc_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return lookAlong_api_sa2b62cbc_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sa2b62cbc_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest.putFloat(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        return lookAlong_api_sa2b62cbc_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_sa2b62cbc_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        return lookAlong_api_s2f25056e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t20, _t13, -(_t21 * _t12)), Math.fma(_t21, _t11, -(_t22 * _t13)), Math.fma(_t22, _t12, -(_t20 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31) {
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        return lookAlong_api_s2f25056e_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest.putFloat(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        return lookAlong_api_s2f25056e_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_api_s2f25056e_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.putFloat(destOffset, axisX * _t1);
        dest.putFloat(destOffset + 4, axisY * _t1);
        dest.putFloat(destOffset + 8, axisZ * _t1);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.putFloat(destOffset, _axisx * _t1);
        dest.putFloat(destOffset + 4, _axisy * _t1);
        dest.putFloat(destOffset + 8, _axisz * _t1);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        return makeRotationLookAlong_api_se3f328e0_1(dest, destOffset, _t11, _t12, _t13, _t39, _t40, _t41, -_t40, -_t39, 1.0f + _t39, _t40 + _t12, _t12 - _t40);
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_se3f328e0_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t39, float _t40, float _t41, float _t42, float _t43, float _t45, float _t49, float _t50) {
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        return makeRotationLookAlong_api_se3f328e0_2(dest, destOffset, _t11, _t12, _t39, _t42, _t49, _t50, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, _t73, _t74, _t75, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_se3f328e0_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t67, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t50);
            dest.putFloat(destOffset + 8, _sp0 * _t70);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t73));
                dest.putFloat(destOffset + 4, _sp3 * _t69);
                dest.putFloat(destOffset + 8, _sp3 * _t49);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t69);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t50);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t49);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 12, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return makeRotationLookAlong_api_s64af833a_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return makeRotationLookAlong_api_s64af833a_2(dest, destOffset, _t11, _t12, _t39, _t42, _t40 + _t12, _t12 - _t40, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t67, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp0, float _sp1) {
        return makeRotationLookAlong_api_s64af833a_3(dest, destOffset, _t11, _t12, _t39, _t42, _t49, _t50, _t61, _t66, _t67, _t69, _t70, _t72, _t73, _t74, _t75, _sp0, _sp1, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_api_s64af833a_3(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t67, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t50);
            dest.putFloat(destOffset + 8, _sp0 * _t70);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t73));
                dest.putFloat(destOffset + 4, _sp3 * _t69);
                dest.putFloat(destOffset + 8, _sp3 * _t49);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t69);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t50);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t49);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 12, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_s5ca64ca6_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_s5ca64ca6_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.putFloat(destOffset, _t15 * _t32);
            dest.putFloat(destOffset + 4, _t17 * _t32);
            dest.putFloat(destOffset + 8, _t16 * _t32);
            dest.putFloat(destOffset + 12, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.putFloat(destOffset, _t30 * _t13);
                dest.putFloat(destOffset + 4, _t30 * _t19);
                dest.putFloat(destOffset + 8, _t30 * _t18);
                dest.putFloat(destOffset + 12, 0.0f);
            } else {
                dest.putFloat(destOffset, 0.0f);
                dest.putFloat(destOffset + 4, 0.0f);
                dest.putFloat(destOffset + 8, 0.0f);
                dest.putFloat(destOffset + 12, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
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
        float _t15 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        float _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_s295abccd_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_s295abccd_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.putFloat(destOffset, _t15 * _t32);
            dest.putFloat(destOffset + 4, _t17 * _t32);
            dest.putFloat(destOffset + 8, _t16 * _t32);
            dest.putFloat(destOffset + 12, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.putFloat(destOffset, _t30 * _t13);
                dest.putFloat(destOffset + 4, _t30 * _t19);
                dest.putFloat(destOffset + 8, _t30 * _t18);
                dest.putFloat(destOffset + 12, 0.0f);
            } else {
                dest.putFloat(destOffset, 0.0f);
                dest.putFloat(destOffset + 4, 0.0f);
                dest.putFloat(destOffset + 8, 0.0f);
                dest.putFloat(destOffset + 12, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationX_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.putFloat(destOffset, _t1);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest.putFloat(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.putFloat(destOffset + 4, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(_t9, _t7, _t12 * _t5));
        dest.putFloat(destOffset + 12, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest.putFloat(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.putFloat(destOffset + 8, Math.fma(_t10, _t5, _t11 * _t7));
        dest.putFloat(destOffset + 12, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, _t1);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest.putFloat(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.putFloat(destOffset + 4, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.putFloat(destOffset + 12, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t5, _t2);
        float _t7 = Math.cosFromSin(_t3, _t0);
        float _t8 = Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        dest.putFloat(destOffset, Math.fma(_t9, _t6, _t12 * _t5));
        dest.putFloat(destOffset + 4, Math.fma(_t10, _t6, _t11 * _t5));
        dest.putFloat(destOffset + 8, Math.fma(_t11, _t6, -(_t10 * _t5)));
        dest.putFloat(destOffset + 12, Math.fma(_t12, _t6, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, _t1);
        dest.putFloat(destOffset + 12, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest.putFloat(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(_t9, _t7, _t12 * _t5));
        dest.putFloat(destOffset + 8, Math.fma(_t10, _t5, _t11 * _t7));
        dest.putFloat(destOffset + 12, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        dest.putFloat(destOffset, Math.fma(_t12, _t5, -(_t9 * _t8)));
        dest.putFloat(destOffset + 4, Math.fma(_t10, _t8, _t11 * _t5));
        dest.putFloat(destOffset + 8, Math.fma(_t11, _t8, -(_t10 * _t5)));
        dest.putFloat(destOffset + 12, Math.fma(_t9, _t5, _t12 * _t8));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t2, -(_selfz * _t1)));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t1, _selfz * _t2));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, _selfz * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t2, -(_selfx * _t1)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, -(_selfy * _t1)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t1, _selfy * _t2));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axis.getFloat(axisOffset) * _t1;
        float _t3 = axis.getFloat(axisOffset + 8) * _t1;
        float _t4 = axis.getFloat(axisOffset + 4) * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_sa0cbaaf7_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_sa0cbaaf7_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
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
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_s842c5910_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_s842c5910_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
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
        dest.putFloat(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t2, _selfz * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t2, -(_selfy * _t1)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest.putFloat(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXYZ_api_s90d7b4fa_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_s90d7b4fa_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dest.putFloat(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXZY_api_sc3db5b04_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_sc3db5b04_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, -(_selfz * _t1)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t1, _selfz * _t2));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest.putFloat(destOffset, Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21)));
        return rotateYXZ_api_s87d88460_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_s87d88460_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.putFloat(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateYZX_api_s2b6abc30_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_s2b6abc30_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.putFloat(destOffset, Math.fma(_selfx, _t2, _selfy * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t2, -(_selfx * _t1)));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.putFloat(destOffset, Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19)));
        return rotateZXY_api_s7d8c3d78_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_s7d8c3d78_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.putFloat(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateZYX_api_se7eefc3e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_se7eefc3e_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putFloat(destOffset + 12, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.putFloat(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX))));
        dest.putFloat(destOffset + 4, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY))));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _t9 = 2.0f * Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.putFloat(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.putFloat(destOffset + 4, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy))));
        dest.putFloat(destOffset + 8, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.putFloat(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX))));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY))));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _t9 = 2.0f * Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.putFloat(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy))));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(d))
                : (float) Math.PI - 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(s));
    }
}
