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
 * to keep generated sources IDE-sized; public only because {@code FloatQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            dest.putFloat(destOffset, -(_selfx * _t3_inv));
            dest.putFloat(destOffset + 4, -(_selfy * _t3_inv));
            dest.putFloat(destOffset + 8, -(_selfz * _t3_inv));
            dest.putFloat(destOffset + 12, _selfw * _t3_inv);
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            dest.putFloat(destOffset, -(_selfx * _t3_inv));
            dest.putFloat(destOffset + 4, -(_selfy * _t3_inv));
            dest.putFloat(destOffset + 8, -(_selfz * _t3_inv));
            dest.putFloat(destOffset + 12, _selfw * _t3_inv);
            return dest;
        }
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        float _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putFloat(destOffset, -(_t20 * _t27_inv));
        dest.putFloat(destOffset + 4, -(_t23 * _t27_inv));
        dest.putFloat(destOffset + 8, -(_t22 * _t27_inv));
        dest.putFloat(destOffset + 12, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        float _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        float _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        float _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        float _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.putFloat(destOffset, -(_t20 * _t27_inv));
        dest.putFloat(destOffset + 4, -(_t23 * _t27_inv));
        dest.putFloat(destOffset + 8, -(_t22 * _t27_inv));
        dest.putFloat(destOffset + 12, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        float _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        float _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        float _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u1);
        dest.putFloat(destOffset, Math.sinWithCos(_t1) * _t4);
        dest.putFloat(destOffset + 4, Math.cosWithSin(_t1) * _t4);
        dest.putFloat(destOffset + 8, Math.sinWithCos(_t3) * _t0);
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t3) * _t0);
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
            if (_m00 > Math.max(_m11, _m22)) {
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
            if (_m00 > Math.max(_m11, _m22)) {
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
            if (_m00 > Math.max(_m11, _m22)) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 36, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 40, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return toMatrix_api_fma_s6c371ec5_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_fma_s6c371ec5_1(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 44, 0.0f);
        dest.putFloat(destOffset + 48, 0.0f);
        dest.putFloat(destOffset + 52, 0.0f);
        dest.putFloat(destOffset + 56, 0.0f);
        dest.putFloat(destOffset + 60, 1.0f);
        return dest;
    }

    /** {@code toMatrix_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.putFloat(destOffset + 20, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putFloat(destOffset + 24, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.putFloat(destOffset + 36, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putFloat(destOffset + 40, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return toMatrix_api_mulAdd_s51da9ef4_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer toMatrix_api_mulAdd_s51da9ef4_1(java.nio.ByteBuffer dest, int destOffset) {
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 12, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 20, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 28, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    /** {@code toMatrix3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.putFloat(destOffset + 12, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.putFloat(destOffset + 16, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putFloat(destOffset + 20, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putFloat(destOffset + 24, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.putFloat(destOffset + 28, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putFloat(destOffset + 32, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer toMatrix3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.putFloat(destOffset + 24, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.putFloat(destOffset + 36, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.putFloat(destOffset + 40, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.putFloat(destOffset + 44, 0.0f);
        return dest;
    }

    /** {@code toMatrix3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer toMatrix3x4_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.putFloat(destOffset + 12, 0.0f);
        dest.putFloat(destOffset + 16, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.putFloat(destOffset + 20, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.putFloat(destOffset + 24, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.putFloat(destOffset + 36, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.putFloat(destOffset + 40, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        swing.putFloat(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.putFloat(swingOffset + 4, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        swing.putFloat(swingOffset + 8, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_api_fma_sfa08fb79_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma_sfa08fb79_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 12, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.putFloat(twistOffset, _t12);
        twist.putFloat(twistOffset + 4, _t13);
        twist.putFloat(twistOffset + 8, _t14);
        twist.putFloat(twistOffset + 12, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        swing.putFloat(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.putFloat(swingOffset + 4, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.putFloat(swingOffset + 8, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_api_mulAdd_s7f38d30e_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd_s7f38d30e_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 12, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        swing.putFloat(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.putFloat(swingOffset + 4, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return decomposeSwingTwist_api_fma_s729f480d_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_fma_s729f480d_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 8, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        swing.putFloat(swingOffset + 12, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.putFloat(twistOffset, _t12);
        twist.putFloat(twistOffset + 4, _t13);
        twist.putFloat(twistOffset + 8, _t14);
        twist.putFloat(twistOffset + 12, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        swing.putFloat(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.putFloat(swingOffset + 4, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.putFloat(swingOffset + 8, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_api_mulAdd_sab015e80_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer decomposeSwingTwist_api_mulAdd_sab015e80_1(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.putFloat(swingOffset + 12, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.putFloat(destOffset + 12, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        FloatQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getSwing_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset);
        return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code getSwing_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_api_fma_s4addf26d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_fma_s4addf26d_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getSwing_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.putFloat(destOffset + 12, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        FloatQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer getTwist_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = java.lang.Math.fma(axisZ, src.getFloat(srcOffset + 8), java.lang.Math.fma(axisX, src.getFloat(srcOffset), axisY * src.getFloat(srcOffset + 4)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getTwist_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = ((axisZ) * (src.getFloat(srcOffset + 8)) + (((axisX) * (src.getFloat(srcOffset)) + (axisY * src.getFloat(srcOffset + 4)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = java.lang.Math.fma(_axisz, src.getFloat(srcOffset + 8), java.lang.Math.fma(_axisx, src.getFloat(srcOffset), _axisy * src.getFloat(srcOffset + 4)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
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

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getTwist_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = ((_axisz) * (src.getFloat(srcOffset + 8)) + (((_axisx) * (src.getFloat(srcOffset)) + (_axisy * src.getFloat(srcOffset + 4)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
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
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
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

    public static java.nio.ByteBuffer nlerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer nlerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, targetW, src.getFloat(srcOffset + 12) * _t0);
        float _t6 = java.lang.Math.fma(alpha, targetZ, src.getFloat(srcOffset + 8) * _t0);
        float _t7 = java.lang.Math.fma(alpha, targetX, src.getFloat(srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, targetY, src.getFloat(srcOffset + 4) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.putFloat(destOffset, _t7 * _t13);
            dest.putFloat(destOffset + 4, _t8 * _t13);
            dest.putFloat(destOffset + 8, _t6 * _t13);
            dest.putFloat(destOffset + 12, _t5 * _t13);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (targetW) + (src.getFloat(srcOffset + 12) * _t0));
        float _t6 = ((alpha) * (targetZ) + (src.getFloat(srcOffset + 8) * _t0));
        float _t7 = ((alpha) * (targetX) + (src.getFloat(srcOffset) * _t0));
        float _t8 = ((alpha) * (targetY) + (src.getFloat(srcOffset + 4) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.putFloat(destOffset, _t7 * _t13);
            dest.putFloat(destOffset + 4, _t8 * _t13);
            dest.putFloat(destOffset + 8, _t6 * _t13);
            dest.putFloat(destOffset + 12, _t5 * _t13);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, target.getFloat(targetOffset + 12), src.getFloat(srcOffset + 12) * _t0);
        float _t6 = java.lang.Math.fma(alpha, target.getFloat(targetOffset + 8), src.getFloat(srcOffset + 8) * _t0);
        float _t7 = java.lang.Math.fma(alpha, target.getFloat(targetOffset), src.getFloat(srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, target.getFloat(targetOffset + 4), src.getFloat(srcOffset + 4) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.putFloat(destOffset, _t7 * _t13);
            dest.putFloat(destOffset + 4, _t8 * _t13);
            dest.putFloat(destOffset + 8, _t6 * _t13);
            dest.putFloat(destOffset + 12, _t5 * _t13);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (target.getFloat(targetOffset + 12)) + (src.getFloat(srcOffset + 12) * _t0));
        float _t6 = ((alpha) * (target.getFloat(targetOffset + 8)) + (src.getFloat(srcOffset + 8) * _t0));
        float _t7 = ((alpha) * (target.getFloat(targetOffset)) + (src.getFloat(srcOffset) * _t0));
        float _t8 = ((alpha) * (target.getFloat(targetOffset + 4)) + (src.getFloat(srcOffset + 4) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.putFloat(destOffset, _t7 * _t13);
            dest.putFloat(destOffset + 4, _t8 * _t13);
            dest.putFloat(destOffset + 8, _t6 * _t13);
            dest.putFloat(destOffset + 12, _t5 * _t13);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return nlerpShortest_api_fma_sb72b7276_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma_sb72b7276_1(java.nio.ByteBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset, _t20 * _t26);
            dest.putFloat(destOffset + 4, _t21 * _t26);
            dest.putFloat(destOffset + 8, _t19 * _t26);
            dest.putFloat(destOffset + 12, _t18 * _t26);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return nlerpShortest_api_mulAdd_sb9c94c3b_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd_sb9c94c3b_1(java.nio.ByteBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset, _t20 * _t26);
            dest.putFloat(destOffset + 4, _t21 * _t26);
            dest.putFloat(destOffset + 8, _t19 * _t26);
            dest.putFloat(destOffset + 12, _t18 * _t26);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return nlerpShortest_api_fma_sbce8caac_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_fma_sbce8caac_1(java.nio.ByteBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset, _t20 * _t26);
            dest.putFloat(destOffset + 4, _t21 * _t26);
            dest.putFloat(destOffset + 8, _t19 * _t26);
            dest.putFloat(destOffset + 12, _t18 * _t26);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return nlerpShortest_api_mulAdd_s667b3511_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer nlerpShortest_api_mulAdd_s667b3511_1(java.nio.ByteBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.putFloat(destOffset, _t20 * _t26);
            dest.putFloat(destOffset + 4, _t21 * _t26);
            dest.putFloat(destOffset + 8, _t19 * _t26);
            dest.putFloat(destOffset + 12, _t18 * _t26);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        return slerp_api_fma_sa51feb_1(dest, destOffset, targetY, targetZ, targetW, _selfy, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_sa51feb_1(java.nio.ByteBuffer dest, int destOffset, float targetY, float targetZ, float targetW, float _selfy, float _selfz, float _selfw, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
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
        dest.putFloat(destOffset, ((_selfx) * (_t25) + (targetX * _t26)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t25) + (targetY * _t26)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t25) + (targetZ * _t26)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, ((_selfx) * (_t13) + (targetX * _t14)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t13) + (targetY * _t14)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t13) + (targetZ * _t14)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t13) + (targetW * _t14)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
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
        return slerp_api_fma_sdebf01c1_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_sdebf01c1_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t25, float _t26) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
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
        dest.putFloat(destOffset, ((_selfx) * (_t25) + (_targetx * _t26)));
        return slerp_api_mulAdd_s24370aee_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s24370aee_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _targety, float _targetz, float _targetw, float _t25, float _t26) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t25) + (_targety * _t26)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t25) + (_targetz * _t26)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        dest.putFloat(destOffset, ((_selfx) * (_t13) + (_targetx * _t14)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t13) + (_targety * _t14)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t13) + (_targetz * _t14)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.ByteBuffer slerpShortest_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return slerpShortest_api_fma_s22ce56d1_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma_s22ce56d1_1(java.nio.ByteBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
            dest.putFloat(destOffset, _t51 * _t45);
            dest.putFloat(destOffset + 4, _t51 * _t46);
            dest.putFloat(destOffset + 8, _t51 * _t44);
            dest.putFloat(destOffset + 12, _t51 * _t43);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return slerpShortest_api_mulAdd_sb2d711ce_1(dest, destOffset, _t43, _t44, _t45, _t46);
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd_sb2d711ce_1(java.nio.ByteBuffer dest, int destOffset, float _t43, float _t44, float _t45, float _t46) {
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.putFloat(destOffset, _t51 * _t45);
            dest.putFloat(destOffset + 4, _t51 * _t46);
            dest.putFloat(destOffset + 8, _t51 * _t44);
            dest.putFloat(destOffset + 12, _t51 * _t43);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return slerpShortest_api_fma_s23f0452f_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_fma_s23f0452f_1(java.nio.ByteBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
            dest.putFloat(destOffset, _t51 * _t45);
            dest.putFloat(destOffset + 4, _t51 * _t46);
            dest.putFloat(destOffset + 8, _t51 * _t44);
            dest.putFloat(destOffset + 12, _t51 * _t43);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return slerpShortest_api_mulAdd_s579f6ab4_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerpShortest_api_mulAdd_s579f6ab4_1(java.nio.ByteBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
            dest.putFloat(destOffset, _t51 * _t45);
            dest.putFloat(destOffset + 4, _t51 * _t46);
            dest.putFloat(destOffset + 8, _t51 * _t44);
            dest.putFloat(destOffset + 12, _t51 * _t43);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
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
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_api_fma_s5498dd39_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33)) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)));
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s5498dd39_1(java.nio.ByteBuffer dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
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
        return squad_api_fma_s5498dd39_2(dest, destOffset, _t12, _t16, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_selfz, _t61, targetZ * _t62), java.lang.Math.fma(_selfx, _t61, targetX * _t62), java.lang.Math.fma(control0Y, _t59, control1Y * _t60), java.lang.Math.fma(_selfy, _t61, targetY * _t62));
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s5498dd39_2(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
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
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_api_mulAdd_sbfc8a026_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_sbfc8a026_1(java.nio.ByteBuffer dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
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
        return squad_api_mulAdd_sbfc8a026_2(dest, destOffset, _t12, _t16, ((control0X) * (_t59) + (control1X * _t60)), _t72, _t73, ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)), ((_selfy) * (_t61) + (targetY * _t62)), _t72 + _t73);
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_sbfc8a026_2(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79) {
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
        dest.putFloat(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.putFloat(destOffset + 4, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.putFloat(destOffset + 8, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.putFloat(destOffset + 12, ((_t72) * (_t105) + (_t73 * _t106)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, float t) {
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
        float _t12 = _t0 * _t1;
        return squad_api_fma_s384e6a97_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)));
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s384e6a97_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
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
        return squad_api_fma_s384e6a97_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s384e6a97_2(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
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
        return squad_api_fma_s384e6a97_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_fma_s384e6a97_3(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, float t) {
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
        float _t12 = _t0 * _t1;
        return squad_api_mulAdd_secbdf434_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_secbdf434_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
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
        return squad_api_mulAdd_secbdf434_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_secbdf434_2(java.nio.ByteBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
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
        return squad_api_mulAdd_secbdf434_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer squad_api_mulAdd_secbdf434_3(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
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
        dest.putFloat(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.putFloat(destOffset + 4, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.putFloat(destOffset + 8, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.putFloat(destOffset + 12, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        dest.putFloat(destOffset + 4, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        dest.putFloat(destOffset + 8, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        dest.putFloat(destOffset + 12, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        dest.putFloat(destOffset + 4, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        dest.putFloat(destOffset + 8, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        dest.putFloat(destOffset + 12, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        dest.putFloat(destOffset + 4, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        dest.putFloat(destOffset + 8, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        dest.putFloat(destOffset + 12, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        dest.putFloat(destOffset + 4, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        dest.putFloat(destOffset + 8, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        dest.putFloat(destOffset + 12, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(weight, otherX, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(weight, otherY, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(weight, otherZ, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(weight, otherW, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((weight) * (otherX) + (_selfx)));
            dest.putFloat(destOffset + 4, ((weight) * (otherY) + (_selfy)));
            dest.putFloat(destOffset + 8, ((weight) * (otherZ) + (_selfz)));
            dest.putFloat(destOffset + 12, ((weight) * (otherW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _otherx = other.getFloat(otherOffset);
            float _othery = other.getFloat(otherOffset + 4);
            float _otherz = other.getFloat(otherOffset + 8);
            float _otherw = other.getFloat(otherOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(weight, _otherx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(weight, _othery, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(weight, _otherz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(weight, _otherw, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _otherx = other.getFloat(otherOffset);
            float _othery = other.getFloat(otherOffset + 4);
            float _otherz = other.getFloat(otherOffset + 8);
            float _otherw = other.getFloat(otherOffset + 12);
            dest.putFloat(destOffset, ((weight) * (_otherx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((weight) * (_othery) + (_selfy)));
            dest.putFloat(destOffset + 8, ((weight) * (_otherz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((weight) * (_otherw) + (_selfw)));
            return dest;
        }
    }

    public static float angle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static float angle_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), src.getFloat(srcOffset + 12));
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), src.getFloat(srcOffset + 12));
        }
    }

    public static float angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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

    /** {@code angleTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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

    public static float angleTo_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static float angleTo_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
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

    /** {@code angleTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
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

    public static java.nio.ByteBuffer axis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer axis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
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
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
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
    }

    public static java.nio.ByteBuffer calculateW_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer calculateW_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            dest.putFloat(destOffset, _selfx);
            dest.putFloat(destOffset + 4, _selfy);
            dest.putFloat(destOffset + 8, _selfz);
            dest.putFloat(destOffset + 12, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f))))));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            dest.putFloat(destOffset, _selfx);
            dest.putFloat(destOffset + 4, _selfy);
            dest.putFloat(destOffset + 8, _selfz);
            dest.putFloat(destOffset + 12, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        float _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        dest.putFloat(destOffset, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        float _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        float _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        float _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        dest.putFloat(destOffset, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        dest.putFloat(destOffset + 4, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        dest.putFloat(destOffset + 8, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        dest.putFloat(destOffset + 12, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _qx = q.getFloat(qOffset);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        float _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        dest.putFloat(destOffset, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        return conjugateBy_api_fma_sfb1d3474_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_fma_sfb1d3474_1(java.nio.ByteBuffer dest, int destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer conjugateBy_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _qx = q.getFloat(qOffset);
        float _qy = q.getFloat(qOffset + 4);
        float _qz = q.getFloat(qOffset + 8);
        float _qw = q.getFloat(qOffset + 12);
        float _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        float _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        float _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        float _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        dest.putFloat(destOffset, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        dest.putFloat(destOffset + 4, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        dest.putFloat(destOffset + 8, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        dest.putFloat(destOffset + 12, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer difference_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer difference_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.putFloat(destOffset + 8, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer difference_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        dest.putFloat(destOffset + 8, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        dest.putFloat(destOffset + 12, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.putFloat(destOffset + 8, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer difference_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.putFloat(destOffset, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        dest.putFloat(destOffset + 4, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        dest.putFloat(destOffset + 8, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        dest.putFloat(destOffset + 12, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
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
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
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
        FloatQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.min(Math.exp(src.getFloat(srcOffset + 12)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.putFloat(destOffset, _selfx * _t10);
            dest.putFloat(destOffset + 4, _selfy * _t10);
            dest.putFloat(destOffset + 8, _selfz * _t10);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer exp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.min(Math.exp(src.getFloat(srcOffset + 12)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.putFloat(destOffset, _selfx * _t10);
            dest.putFloat(destOffset + 4, _selfy * _t10);
            dest.putFloat(destOffset + 8, _selfz * _t10);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(_t11, _t14));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t12, _t13));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(_t11, _t14));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t12, _t13));
            dest.putFloat(destOffset + 8, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.putFloat(destOffset, Math.atan2(_t11, _t14));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t12, _t13));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.putFloat(destOffset, Math.atan2(_t11, _t14));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t12, _t13));
            dest.putFloat(destOffset + 8, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        return getEulerAnglesXZY_api_seabef428_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_seabef428_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 4, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.putFloat(destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.putFloat(destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.putFloat(destOffset + 4, 0.0f);
        } else {
            dest.putFloat(destOffset, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 4, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 8, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 8, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.putFloat(destOffset + 4, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(_t9, _t11));
            dest.putFloat(destOffset + 8, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_t12, _t15));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.putFloat(destOffset + 4, Math.atan2(_t13, _t14));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_t12, _t15));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.putFloat(destOffset + 4, Math.atan2(_t13, _t14));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_t12, _t15));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.putFloat(destOffset + 4, Math.atan2(_t13, _t14));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, Math.atan2(_t12, _t15));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.putFloat(destOffset + 4, Math.atan2(_t13, _t14));
        }
        dest.putFloat(destOffset + 8, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_t11, _t14));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.putFloat(destOffset + 8, Math.atan2(_t12, _t13));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_t11, _t14));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.putFloat(destOffset + 8, Math.atan2(_t12, _t13));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_t11, _t14));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.putFloat(destOffset + 8, Math.atan2(_t12, _t13));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(_t11, _t14));
        } else {
            dest.putFloat(destOffset + 4, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.putFloat(destOffset + 8, Math.atan2(_t12, _t13));
        }
        dest.putFloat(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t9 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.putFloat(destOffset + 8, Math.atan2(_t8, _t10));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.putFloat(destOffset + 8, Math.atan2(_t8, _t10));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer getEulerAnglesZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t9 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.putFloat(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.putFloat(destOffset + 8, Math.atan2(_t8, _t10));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer getEulerAnglesZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 8, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.putFloat(destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.putFloat(destOffset + 8, Math.atan2(_t8, _t10));
        }
        dest.putFloat(destOffset + 4, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.ByteBuffer integrate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.ByteBuffer integrate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        dest.putFloat(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, float dt) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.getFloat(angularVelOffset + 8) * _t0;
        float _t2 = angularVel.getFloat(angularVelOffset) * _t0;
        float _t3 = angularVel.getFloat(angularVelOffset + 4) * _t0;
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_api_fma_s3034ffc2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer integrate_api_fma_s3034ffc2_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t15, float _t16, float _t17) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer integrate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, float dt) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.getFloat(angularVelOffset + 8) * _t0;
        float _t2 = angularVel.getFloat(angularVelOffset) * _t0;
        float _t3 = angularVel.getFloat(angularVelOffset + 4) * _t0;
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
        dest.putFloat(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
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

    /** {@code invNegativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
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

    /** {@code invNegativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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

    /** {@code invNegativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invNegativeZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            dest.putFloat(destOffset + 4, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putFloat(destOffset + 8, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            dest.putFloat(destOffset + 4, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putFloat(destOffset + 8, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            dest.putFloat(destOffset + 8, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putFloat(destOffset + 4, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            dest.putFloat(destOffset + 8, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.putFloat(destOffset + 4, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.putFloat(destOffset + 4, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.putFloat(destOffset + 8, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putFloat(destOffset + 4, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.putFloat(destOffset + 8, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invNormalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.putFloat(destOffset + 8, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer invPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
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

    /** {@code invPositiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
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

    /** {@code invPositiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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

    /** {@code invPositiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invPositiveZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
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
        FloatQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
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
        dest.putFloat(destOffset + 12, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer log_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
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
        dest.putFloat(destOffset + 12, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
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

    /** {@code negativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
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

    /** {@code negativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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

    /** {@code negativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer negativeZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
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

    public static java.nio.ByteBuffer normalizedNegativeX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            dest.putFloat(destOffset + 4, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putFloat(destOffset + 8, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            dest.putFloat(destOffset + 4, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putFloat(destOffset + 8, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedNegativeY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            dest.putFloat(destOffset + 8, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putFloat(destOffset + 4, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            dest.putFloat(destOffset + 8, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedNegativeZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.putFloat(destOffset + 4, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.putFloat(destOffset + 4, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.putFloat(destOffset + 8, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.putFloat(destOffset + 4, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.putFloat(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.putFloat(destOffset + 4, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.putFloat(destOffset + 8, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalizedPositiveZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.putFloat(destOffset + 4, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.putFloat(destOffset + 4, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.putFloat(destOffset + 8, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer positiveX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer positiveX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
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

    /** {@code positiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
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

    /** {@code positiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
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

    /** {@code positiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer positiveZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_api_fma(dest, destOffset, src, srcOffset, t);
        return pow_api_mulAdd(dest, destOffset, src, srcOffset, t);
    }

    /** {@code pow_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer pow_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return pow_api_fma_s9b77c935_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer pow_api_fma_s9b77c935_1(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        if (_t25 > 0.0f) {
            dest.putFloat(destOffset, _t21 * _t31);
            dest.putFloat(destOffset + 4, _t22 * _t31);
            dest.putFloat(destOffset + 8, _t20 * _t31);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer pow_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return pow_api_mulAdd_sf8dbaf7e_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer pow_api_mulAdd_sf8dbaf7e_1(java.nio.ByteBuffer dest, int destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        if (_t25 > 0.0f) {
            dest.putFloat(destOffset, _t21 * _t31);
            dest.putFloat(destOffset + 4, _t22 * _t31);
            dest.putFloat(destOffset + 8, _t20 * _t31);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTowards_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTowards_api_fma_s3148ad80_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s3148ad80_1(java.nio.ByteBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
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
        return rotateTowards_api_fma_s3148ad80_2(dest, destOffset, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_s3148ad80_2(java.nio.ByteBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            dest.putFloat(destOffset, _t87 * _t81);
            dest.putFloat(destOffset + 4, _t87 * _t82);
            dest.putFloat(destOffset + 8, _t87 * _t80);
            dest.putFloat(destOffset + 12, _t87 * _t79);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTowards_api_mulAdd_sa23dd2c3_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f);
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sa23dd2c3_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t55) {
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
        return rotateTowards_api_mulAdd_sa23dd2c3_2(dest, destOffset, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sa23dd2c3_2(java.nio.ByteBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            dest.putFloat(destOffset, _t87 * _t81);
            dest.putFloat(destOffset + 4, _t87 * _t82);
            dest.putFloat(destOffset + 8, _t87 * _t80);
            dest.putFloat(destOffset + 12, _t87 * _t79);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return rotateTowards_api_fma_sc4f97b76_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30)), java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20))));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sc4f97b76_1(java.nio.ByteBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37, float _t38) {
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
        return rotateTowards_api_fma_sc4f97b76_2(dest, destOffset, _t79, _t80, _t81, _t82);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_fma_sc4f97b76_2(java.nio.ByteBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82) {
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            dest.putFloat(destOffset, _t87 * _t81);
            dest.putFloat(destOffset + 4, _t87 * _t82);
            dest.putFloat(destOffset + 8, _t87 * _t80);
            dest.putFloat(destOffset + 12, _t87 * _t79);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float step) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _targetx = target.getFloat(targetOffset);
        float _targety = target.getFloat(targetOffset + 4);
        float _targetz = target.getFloat(targetOffset + 8);
        float _targetw = target.getFloat(targetOffset + 12);
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
        return rotateTowards_api_mulAdd_sf6989c9_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37, _t38, _t37 * _t38);
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sf6989c9_1(java.nio.ByteBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37, float _t38, float _t43) {
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
        return rotateTowards_api_mulAdd_sf6989c9_2(dest, destOffset, _t79, _t80, _t81, _t82, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTowards_api_mulAdd_sf6989c9_2(java.nio.ByteBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86) {
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            dest.putFloat(destOffset, _t87 * _t81);
            dest.putFloat(destOffset + 4, _t87 * _t82);
            dest.putFloat(destOffset + 8, _t87 * _t80);
            dest.putFloat(destOffset + 12, _t87 * _t79);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_fma_s49fc4fd4_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s49fc4fd4_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_fma_s49fc4fd4_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s49fc4fd4_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_s49fc4fd4_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s49fc4fd4_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_mulAdd_s81b66cf1_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s81b66cf1_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_mulAdd_s81b66cf1_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s81b66cf1_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s81b66cf1_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s81b66cf1_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 8, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_fma_se614a2b2_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se614a2b2_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_fma_se614a2b2_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se614a2b2_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_api_fma_se614a2b2_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se614a2b2_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_mulAdd_s225218d7_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s225218d7_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_mulAdd_s225218d7_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s225218d7_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s225218d7_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s225218d7_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 8, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_fma_sb591f666_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
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
        return lookAlong_lh_api_fma_sb591f666_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_sb591f666_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sb591f666_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_mulAdd_sc04bd8c5_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30)))));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33) {
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
        return lookAlong_lh_api_mulAdd_sc04bd8c5_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_sc04bd8c5_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_sc04bd8c5_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 8, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_fma_se523af34_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
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
        return lookAlong_rh_api_fma_se523af34_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75) {
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
        return lookAlong_rh_api_fma_se523af34_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_se523af34_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_mulAdd_s9643727b_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
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
        return lookAlong_rh_api_mulAdd_s9643727b_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_api_mulAdd_s9643727b_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_s9643727b_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_fma_s1872ff18_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s1872ff18_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_fma_s1872ff18_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s1872ff18_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_s1872ff18_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_s1872ff18_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_mulAdd_s7e0def17_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s7e0def17_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_mulAdd_s7e0def17_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s7e0def17_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s7e0def17_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_s7e0def17_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 8, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_fma_s4ed951ae_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s4ed951ae_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_fma_s4ed951ae_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s4ed951ae_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_api_fma_s4ed951ae_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s4ed951ae_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_mulAdd_sbc89c6ad_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sbc89c6ad_1(java.nio.ByteBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_mulAdd_sbc89c6ad_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sbc89c6ad_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_sbc89c6ad_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sbc89c6ad_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 8, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_fma_sa6db55b2_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sa6db55b2_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
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
        return lookAlong_lh_api_fma_sa6db55b2_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sa6db55b2_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_sa6db55b2_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_fma_sa6db55b2_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_lh_api_mulAdd_safcfb0cb_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_safcfb0cb_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
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
        return lookAlong_lh_api_mulAdd_safcfb0cb_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_safcfb0cb_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
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
        dest.putFloat(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        return lookAlong_lh_api_mulAdd_safcfb0cb_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_lh_api_mulAdd_safcfb0cb_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer lookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer lookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_fma_s254df2f8_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s254df2f8_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
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
        return lookAlong_rh_api_fma_s254df2f8_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s254df2f8_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75) {
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
        return lookAlong_rh_api_fma_s254df2f8_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_fma_s254df2f8_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return lookAlong_rh_api_mulAdd_sd0a6bfa1_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sd0a6bfa1_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
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
        return lookAlong_rh_api_mulAdd_sd0a6bfa1_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sd0a6bfa1_2(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_api_mulAdd_sd0a6bfa1_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer lookAlong_rh_api_mulAdd_sd0a6bfa1_3(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationAxis_api(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dest.putFloat(destOffset, axisX * _t1);
        dest.putFloat(destOffset + 4, axisY * _t1);
        dest.putFloat(destOffset + 8, axisZ * _t1);
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t0));
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
        float _t1 = Math.sinWithCos(_t0);
        dest.putFloat(destOffset, _axisx * _t1);
        dest.putFloat(destOffset + 4, _axisy * _t1);
        dest.putFloat(destOffset + 8, _axisz * _t1);
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_lh_api_fma_s127225f8_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s127225f8_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_api_fma_s127225f8_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s127225f8_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_lh_api_mulAdd_s873d6555_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s873d6555_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49, float _t60) {
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_s873d6555_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s873d6555_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_rh_api_fma_s6fe5f686_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s6fe5f686_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_api_fma_s6fe5f686_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s6fe5f686_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51, float _t62) {
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_sb1ba3fb3_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_lh_api_fma_sef1062f2_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return makeRotationLookAlong_lh_api_fma_sef1062f2_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1) {
        return makeRotationLookAlong_lh_api_fma_sef1062f2_3(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, _sp0, _sp1, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_sef1062f2_3(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return makeRotationLookAlong_lh_api_mulAdd_s434491b1_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_s434491b1_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        return makeRotationLookAlong_rh_api_fma_s4a7ddb90_3(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s4a7ddb90_3(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_1(java.nio.ByteBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeRotationLookAlong_rh_api_mulAdd_s24d9588f_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s24d9588f_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_lh_api_fma_s1d16192c_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s1d16192c_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_api_fma_s1d16192c_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s1d16192c_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_lh_api_mulAdd_se9291a5b_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se9291a5b_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49, float _t60) {
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_api_mulAdd_se9291a5b_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_se9291a5b_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_rh_api_fma_s37c8ed52_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s37c8ed52_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_api_fma_s37c8ed52_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_s37c8ed52_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_rh_api_mulAdd_s91892b99_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s91892b99_1(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51, float _t62) {
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_api_mulAdd_s91892b99_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s91892b99_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_lh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_lh_api_fma_s66c78c6e_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s66c78c6e_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return makeRotationLookAlong_lh_api_fma_s66c78c6e_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s66c78c6e_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1) {
        return makeRotationLookAlong_lh_api_fma_s66c78c6e_3(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, _sp0, _sp1, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_fma_s66c78c6e_3(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_lh_api_mulAdd_sd5e4a757_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t65);
            dest.putFloat(destOffset + 4, _sp0 * _t49);
            dest.putFloat(destOffset + 8, _sp0 * _t69);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.putFloat(destOffset + 4, _sp3 * _t68);
                dest.putFloat(destOffset + 8, _sp3 * _t48);
                dest.putFloat(destOffset + 12, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.putFloat(destOffset, _sp1 * _t68);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.putFloat(destOffset + 8, _sp1 * _t66);
                    dest.putFloat(destOffset + 12, _sp1 * _t49);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t48);
                    dest.putFloat(destOffset + 4, _sp2 * _t66);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.putFloat(destOffset + 12, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationLookAlong_rh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_rh_api_fma_sa41768e4_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_sa41768e4_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeRotationLookAlong_rh_api_fma_sa41768e4_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_sa41768e4_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        return makeRotationLookAlong_rh_api_fma_sa41768e4_3(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_fma_sa41768e4_3(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.getFloat(dirOffset);
        float _diry = dir.getFloat(dirOffset + 4);
        float _dirz = dir.getFloat(dirOffset + 8);
        float _upx = up.getFloat(upOffset);
        float _upy = up.getFloat(upOffset + 4);
        float _upz = up.getFloat(upOffset + 8);
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
        return makeRotationLookAlong_rh_api_mulAdd_s45260d45_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s45260d45_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return makeRotationLookAlong_rh_api_mulAdd_s45260d45_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationLookAlong_rh_api_mulAdd_s45260d45_2(java.nio.ByteBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.putFloat(destOffset, _sp0 * _t67);
            dest.putFloat(destOffset + 4, _sp0 * _t51);
            dest.putFloat(destOffset + 8, _sp0 * _t71);
            dest.putFloat(destOffset + 12, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.putFloat(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.putFloat(destOffset + 4, _sp3 * _t70);
                dest.putFloat(destOffset + 8, _sp3 * _t50);
                dest.putFloat(destOffset + 12, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.putFloat(destOffset, _sp1 * _t70);
                    dest.putFloat(destOffset + 4, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.putFloat(destOffset + 8, _sp1 * _t68);
                    dest.putFloat(destOffset + 12, _sp1 * _t51);
                } else {
                    dest.putFloat(destOffset, _sp2 * _t50);
                    dest.putFloat(destOffset + 4, _sp2 * _t68);
                    dest.putFloat(destOffset + 8, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.putFloat(destOffset + 12, _sp2 * _t71);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        return makeRotationTo_api_fma_se9c59cdb_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_se9c59cdb_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        return makeRotationTo_api_mulAdd_s338611b8_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s338611b8_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
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
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_s2cf4665c_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_s2cf4665c_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
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
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s469c8e4f_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s469c8e4f_1(java.nio.ByteBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        return makeRotationTo_api_fma_s3a45b581_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_s3a45b581_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
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

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        return makeRotationTo_api_mulAdd_s1b8b3e6c_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s1b8b3e6c_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
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

    public static java.nio.ByteBuffer makeRotationTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
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
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_api_fma_sf0ccbae0_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_fma_sf0ccbae0_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
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

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
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
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_api_mulAdd_s859cc285_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer makeRotationTo_api_mulAdd_s859cc285_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
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
        dest.putFloat(destOffset, Math.sinWithCos(_t0));
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXYZ_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_api_mulAdd(dest, destOffset, angleX, angleY, angleZ);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.putFloat(destOffset + 8, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putFloat(destOffset + 12, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationXZY_api(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_api_mulAdd(dest, destOffset, angleX, angleZ, angleY);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.putFloat(destOffset + 8, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.putFloat(destOffset + 12, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationY_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, Math.sinWithCos(_t0));
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYXZ_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_api_mulAdd(dest, destOffset, angleY, angleX, angleZ);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.putFloat(destOffset + 8, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.putFloat(destOffset + 12, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationYZX_api(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_api_mulAdd(dest, destOffset, angleY, angleZ, angleX);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putFloat(destOffset + 8, ((_t12) * (_t5) - (_t11 * _t8)));
        dest.putFloat(destOffset + 12, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZ_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, Math.sinWithCos(_t0));
        dest.putFloat(destOffset + 12, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZXY_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_api_mulAdd(dest, destOffset, angleZ, angleX, angleY);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.putFloat(destOffset + 8, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.putFloat(destOffset + 12, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotationZYX_api(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_api_mulAdd(dest, destOffset, angleZ, angleY, angleX);
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
        dest.putFloat(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer makeRotationZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
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
        dest.putFloat(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.putFloat(destOffset + 4, ((_t11) * (_t8) + (_t12 * _t5)));
        dest.putFloat(destOffset + 8, ((_t12) * (_t8) - (_t11 * _t5)));
        dest.putFloat(destOffset + 12, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 4, ((_selfy) * (_t1) - (_selfz * _t2)));
            dest.putFloat(destOffset + 8, ((_selfy) * (_t2) + (_selfz * _t1)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer preRotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfz * _t2)));
            dest.putFloat(destOffset + 4, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 8, ((_selfz) * (_t1) - (_selfx * _t2)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer preRotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) - (_selfy * _t2)));
            dest.putFloat(destOffset + 4, ((_selfx) * (_t2) + (_selfy * _t1)));
            dest.putFloat(destOffset + 8, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axis.getFloat(axisOffset) * _t2;
        float _t4 = axis.getFloat(axisOffset + 8) * _t2;
        float _t5 = axis.getFloat(axisOffset + 4) * _t2;
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axis.getFloat(axisOffset) * _t2;
        float _t4 = axis.getFloat(axisOffset + 8) * _t2;
        float _t5 = axis.getFloat(axisOffset + 4) * _t2;
        dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_se116e59e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_se116e59e_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_s2d97e017_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_s2d97e017_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTo_api_fma_see328ad5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_see328ad5_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTo_api_mulAdd_sa8bf2eb0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_sa8bf2eb0_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_s42606f2a_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_s42606f2a_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_s35c7c96d_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_s35c7c96d_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer rotateTo_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTo_api_fma_s1ba2eedb_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_fma_s1ba2eedb_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
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
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.getFloat(fromDirOffset);
        float _fromDiry = fromDir.getFloat(fromDirOffset + 4);
        float _fromDirz = fromDir.getFloat(fromDirOffset + 8);
        float _toDirx = toDir.getFloat(toDirOffset);
        float _toDiry = toDir.getFloat(toDirOffset + 4);
        float _toDirz = toDir.getFloat(toDirOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
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
        return rotateTo_api_mulAdd_sd7532684_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateTo_api_mulAdd_sd7532684_1(java.nio.ByteBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
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
        dest.putFloat(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 4, ((_selfy) * (_t1) + (_selfz * _t2)));
            dest.putFloat(destOffset + 8, ((_selfz) * (_t1) - (_selfy * _t2)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateXYZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXYZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXYZ_api_fma_s4cd24c0b_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_fma_s4cd24c0b_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXYZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dest.putFloat(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXZY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXZY_api_fma_s420c885d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_fma_s420c885d_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateXZY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dest.putFloat(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) - (_selfz * _t2)));
            dest.putFloat(destOffset + 4, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 8, ((_selfx) * (_t2) + (_selfz * _t1)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateYXZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYXZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        return rotateYXZ_api_fma_sd4cb8c19_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_fma_sd4cb8c19_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYXZ_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dest.putFloat(destOffset, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYZX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateYZX_api_fma_sc514d1e1_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_fma_sc514d1e1_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateYZX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.putFloat(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.putFloat(destOffset, ((_selfx) * (_t1) + (_selfy * _t2)));
            dest.putFloat(destOffset + 4, ((_selfy) * (_t1) - (_selfx * _t2)));
            dest.putFloat(destOffset + 8, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.putFloat(destOffset + 12, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateZXY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZXY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        return rotateZXY_api_fma_sca9185f1_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_fma_sca9185f1_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZXY_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.putFloat(destOffset, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZYX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateZYX_api_fma_s7d19b967_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_fma_s7d19b967_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateZYX_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dest.putFloat(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.ByteBuffer transform_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transform_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (vY) - (_selfy * vX));
        float _t10 = 2.0f * ((_selfz) * (vX) - (_selfx * vZ));
        float _t11 = 2.0f * ((_selfy) * (vZ) - (_selfz * vY));
        dest.putFloat(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transform_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_vy) - (_selfy * _vx));
        float _t10 = 2.0f * ((_selfz) * (_vx) - (_selfx * _vz));
        float _t11 = 2.0f * ((_selfy) * (_vz) - (_selfz * _vy));
        dest.putFloat(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        dest.putFloat(destOffset + 8, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer transformInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transformInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (vZ) - (_selfz * vX));
        float _t10 = 2.0f * ((_selfy) * (vX) - (_selfx * vY));
        float _t11 = 2.0f * ((_selfz) * (vY) - (_selfy * vZ));
        dest.putFloat(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer transformInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((_selfx) * (_vz) - (_selfz * _vx));
        float _t10 = 2.0f * ((_selfy) * (_vx) - (_selfx * _vy));
        float _t11 = 2.0f * ((_selfz) * (_vy) - (_selfy * _vz));
        dest.putFloat(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        dest.putFloat(destOffset + 8, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
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
