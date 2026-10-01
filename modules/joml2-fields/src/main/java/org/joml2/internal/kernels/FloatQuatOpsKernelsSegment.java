// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code FloatQuatOps} and its sibling kernel units. Not public API.
 */
public final class FloatQuatOpsKernelsSegment {
    private FloatQuatOpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment invert_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invert_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_selfx * _t3_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_selfy * _t3_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_selfz * _t3_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t3_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        float _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t23 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t22 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t21 * _t27_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        float _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        return invertProduct_api_s1bfcea86_1(dest, destOffset, _t21, _t22, _t23, _t27_inv);
    }

    /** Piece 2 of {@code invertProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_s1bfcea86_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t21, float _t22, float _t23, float _t27_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t23 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t22 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t21 * _t27_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ + _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, otherW + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz + _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _otherw + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, scalar * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, scalar * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - otherZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw - otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - _otherz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw - _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformRotation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u1, float u2, float u3) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeUniformRotation_unsafe(_destBase, u1, u2, u3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformRotation_api(java.lang.foreign.MemorySegment dest, long destOffset, float u1, float u2, float u3) {
        float _t0 = (float) java.lang.Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u1);
        float _t6 = Math.sin(_t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t4 * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosFromSin(_t4, _t1) * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t6 * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t6, _t3) * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ, float vW) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, vZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, vW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _vw = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _vz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _vw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, dqRX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, dqRY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, dqRZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, dqRW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _m00 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset);
        float _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m20 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        float _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m21 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m02 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m12 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 28L);
        float _m22 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _t0 = _m00 + _m11;
        float _t1 = _m21 - _m12;
        float _t4 = _m01 + _m10;
        float _t6 = _m02 + _m20;
        float _t7 = _m02 - _m20;
        float _t8 = _m12 + _m21;
        float _t9 = _m10 - _m01;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t10 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _m00 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset);
        float _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m02 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m12 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m20 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _m21 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 36L);
        float _m22 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 40L);
        float _t0 = _m00 + _m11;
        float _t1 = _m21 - _m12;
        float _t4 = _m01 + _m10;
        float _t6 = _m02 + _m20;
        float _t7 = _m02 - _m20;
        float _t8 = _m12 + _m21;
        float _t9 = _m10 - _m01;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t10 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _destBase = dest.address() + destOffset;
        long _mBase = m.address() + mOffset;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeFromMatrixMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _m00 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset);
        float _m10 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m20 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m01 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m11 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m21 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m02 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _m12 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 36L);
        float _m22 = m.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, mOffset + 40L);
        float _t0 = _m00 + _m11;
        float _t1 = _m21 - _m12;
        float _t4 = _m01 + _m10;
        float _t6 = _m02 + _m20;
        float _t7 = _m02 - _m20;
        float _t8 = _m12 + _m21;
        float _t9 = _m10 - _m01;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t10 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return toMatrix_api_s165c7c0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2);
    }

    /** Piece 2 of {@code toMatrix_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix_api_s165c7c0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 48L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 52L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 56L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 60L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        return toMatrix3x3_api_saf7ea7a2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x3_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x3_api_saf7ea7a2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return toMatrix3x4_api_sdd05c61_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2);
    }

    /** Piece 2 of {@code toMatrix3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x4_api_sdd05c61_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment decomposeSwingTwist_unsafe(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        long _swingBase = swing.address() + swingOffset;
        long _twistBase = twist.address() + twistOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, axisX, axisY, axisZ);
        return swing;
    }

    public static java.lang.foreign.MemorySegment decomposeSwingTwist_api(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return decomposeSwingTwist_api_sb6856e57_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_sb6856e57_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset, _t12);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 4L, _t13);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 8L, _t14);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 12L, _t11);
        return swing;
    }

    public static java.lang.foreign.MemorySegment decomposeSwingTwist_unsafe(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        long _swingBase = swing.address() + swingOffset;
        long _twistBase = twist.address() + twistOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, _axisBase);
        return swing;
    }

    public static java.lang.foreign.MemorySegment decomposeSwingTwist_api(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
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
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        return decomposeSwingTwist_api_s55ddb702_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_s55ddb702_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset, _t12);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 4L, _t13);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 8L, _t14);
        twist.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, twistOffset + 12L, _t11);
        return swing;
    }

    public static java.lang.foreign.MemorySegment getSwing_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getSwing_unsafe(_destBase, _srcBase, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getSwing_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return getSwing_api_s189252a2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_s189252a2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getSwing_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.getSwing_unsafe(_destBase, _srcBase, _axisBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getSwing_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        return getSwing_api_s2ecaa7f7_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_s2ecaa7f7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getTwist_unsafe(_destBase, _srcBase, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = Math.fma(axisZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(axisX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), axisY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, axisX * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, axisY * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, axisZ * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.getTwist_unsafe(_destBase, _srcBase, _axisBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t2 = Math.fma(_axisz, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(_axisx, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _axisy * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _axisx * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _axisy * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _axisz * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, otherZ - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, _otherz - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        float _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        float _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        float _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t7 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t5 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t4 * _t12);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = Math.fma(alpha, target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L) - _selfw, _selfw);
        float _t5 = Math.fma(alpha, target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L) - _selfz, _selfz);
        float _t6 = Math.fma(alpha, target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset) - _selfx, _selfx);
        float _t7 = Math.fma(alpha, target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L) - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        return nlerp_api_s2408b1b6_1(dest, destOffset, _t4, _t5, _t6, _t7, _t11, (1.0f / (float) java.lang.Math.sqrt(_t11)));
    }

    /** Piece 2 of {@code nlerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_s2408b1b6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t4, float _t5, float _t6, float _t7, float _t11, float _t12) {
        if (_t11 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t6 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t7 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t5 * _t12);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t4 * _t12);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return nlerpShortest_api_sa71e9301_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_sa71e9301_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t19 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t18 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t17 * _t25);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return nlerpShortest_api_s556f8cc8_1(dest, destOffset, _t17, _t18, _t19, _t20);
    }

    /** Piece 2 of {@code nlerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_s556f8cc8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t17, float _t18, float _t19, float _t20) {
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t19 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t18 * _t25);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t17 * _t25);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t27, targetX * _t26));
        return slerp_api_s1d4b8f24_1(dest, destOffset, targetY, targetZ, targetW, _selfy, _selfz, _selfw, _t26, _t27);
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s1d4b8f24_1(java.lang.foreign.MemorySegment dest, long destOffset, float targetY, float targetZ, float targetW, float _selfy, float _selfz, float _selfw, float _t26, float _t27) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t27, targetY * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t27, targetZ * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t27, targetW * _t26));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        return slerp_api_sa87edfaf_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, 1.0f - alpha, alpha < 0.5f ? 1.0f : 0.0f, java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4)))));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_sa87edfaf_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t5, float _t11) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t27, _targetx * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t27, _targety * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t27, _targetz * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t27, _targetw * _t26));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return slerpShortest_api_se5a4cb22_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_se5a4cb22_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t50 * _t44);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t50 * _t45);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t50 * _t43);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t50 * _t42);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return slerpShortest_api_s662b8789_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_s662b8789_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
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
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t50 * _t44);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t50 * _t45);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t50 * _t43);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t50 * _t42);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment squad_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment squad_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        return squad_api_s34c6a92a_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, t + t);
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_s34c6a92a_1(java.lang.foreign.MemorySegment dest, long destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t12, float _t55, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1) {
        float _t8 = _selfw + targetW;
        float _t9 = _selfz + targetZ;
        float _t10 = _selfx + targetX;
        float _t11 = _selfy + targetY;
        float _t13 = _t0 * _t1;
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
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(_selfw, _t58, targetW * _t56);
        return squad_api_s34c6a92a_2(dest, destOffset, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, Math.fma(control0X, _t57, control1X * _t55), _t68, _t69, Math.fma(control0Z, _t57, control1Z * _t55), Math.fma(_selfz, _t58, targetZ * _t56), Math.fma(_selfx, _t58, targetX * _t56), Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69);
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_s34c6a92a_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t73, _t98, _t74 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t70, _t98, _t71 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.lang.foreign.MemorySegment squad_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment control0, long control0Offset, java.lang.foreign.MemorySegment control1, long control1Offset, java.lang.foreign.MemorySegment target, long targetOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _control0Base = control0.address() + control0Offset;
        long _control1Base = control1.address() + control1Offset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, _control0Base, _control1Base, _targetBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment squad_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment control0, long control0Offset, java.lang.foreign.MemorySegment control1, long control1Offset, java.lang.foreign.MemorySegment target, long targetOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _control0x = control0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control0Offset);
        float _control0y = control0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control0Offset + 4L);
        float _control0z = control0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control0Offset + 8L);
        float _control0w = control0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control0Offset + 12L);
        float _control1x = control1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control1Offset);
        float _control1y = control1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control1Offset + 4L);
        float _control1z = control1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control1Offset + 8L);
        float _control1w = control1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, control1Offset + 12L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        return squad_api_sdc2af979_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw);
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_sdc2af979_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw) {
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = _control0w + _control1w;
        float _t4 = _control0z + _control1z;
        float _t5 = _control0x + _control1x;
        float _t6 = _control0y + _control1y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = _selfw + _targetw;
        float _t9 = _selfz + _targetz;
        float _t10 = _selfx + _targetx;
        float _t11 = _selfy + _targety;
        float _t13 = _t0 * _t1;
        return squad_api_sdc2af979_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, 1.0f - _t7, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, java.lang.Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** Piece 3 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_sdc2af979_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
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
        return squad_api_sdc2af979_3(dest, destOffset, _t13, _t14, _t17, Math.fma(_control0x, _t57, _control1x * _t55), Math.fma(_control0w, _t57, _control1w * _t55), Math.fma(_selfw, _t58, _targetw * _t56), Math.fma(_control0z, _t57, _control1z * _t55), Math.fma(_selfz, _t58, _targetz * _t56), Math.fma(_selfx, _t58, _targetx * _t56), Math.fma(_control0y, _t57, _control1y * _t55), Math.fma(_selfy, _t58, _targety * _t56));
    }

    /** Piece 4 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_sdc2af979_3(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t73, _t98, _t74 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t70, _t98, _t71 * _t99));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(weight, otherX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(weight, otherY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(weight, otherZ, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(weight, otherW, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(weight, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(weight, _othery, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(weight, _otherz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    public static float angle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static float angle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        return 2.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static float angleTo_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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

    public static float angleTo_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static float angleTo_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
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
        return angleTo_api_s607d604c_1(_selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _selfw + _t9, _selfz + _t10, _selfx + _t11, _selfy + _t12);
    }

    /** Piece 2 of {@code angleTo_api}, split to fit the inline budget; reached only through it. */
    private static float angleTo_api_s607d604c_1(float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static java.lang.foreign.MemorySegment axis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment axis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        float _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)));
        return conjugateBy_api_sbaf86c6b_1(dest, destOffset, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_sbaf86c6b_1(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _qBase = q.address() + qOffset;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _qx = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset);
        float _qy = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qz = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qw = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        float _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23)));
        return conjugateBy_api_sd00c2c6_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_sd00c2c6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        return difference_api_s2907d24c_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t3_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code difference_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_s2907d24c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t3_inv, float _sp1, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(otherZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(otherX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = java.lang.Math.min(Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t7 = Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t10);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t10);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t10);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t7, _t5) * _t2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesXYZ_api_s897ef46a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _selfz * _selfz, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), _t9, _t10, Math.fma(_t10, _t10, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_s897ef46a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t3, float _t8, float _t9, float _t10, float _t12) {
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, _t10));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfy * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _t1);
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        return getEulerAnglesXZY_api_s5434e8d4_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, _t1, _t7, 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t9, Math.fma(_t9, _t9, _t7 * _t7));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_s5434e8d4_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t1, float _t7, float _t8, float _t9, float _t11) {
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t7, _t9));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f)));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_api_s48380508_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _selfz * _selfz, _t8, 2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), _t10, Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_s48380508_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t3, float _t8, float _t9, float _t10, float _t12) {
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t8, _t10));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesYZX_api_sc1e60f50_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), _t8, _t9, Math.fma(_t9, _t9, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_sc1e60f50_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t7, float _t8, float _t9, float _t11) {
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t8, _t9));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t1), 1.0f);
        return getEulerAnglesZXY_api_s711bc150_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz), _t8, _t9, Math.fma(_t9, _t9, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_s711bc150_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t7, float _t8, float _t9, float _t11) {
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t1), 1.0f)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, _t9));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesZYX_api_s5a0beb6e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, _t7, 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t9, Math.fma(_t9, _t9, _t7 * _t7));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_s5a0beb6e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t7, float _t8, float _t9, float _t11) {
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t7, _t9));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        return integrate_api_s3e338747_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_s3e338747_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment angularVel, long angularVelOffset, float dt) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _angularVelBase = angularVel.address() + angularVelOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, _angularVelBase, dt);
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment angularVel, long angularVelOffset, float dt) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, angularVelOffset + 8L) * _t0;
        float _t2 = angularVel.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, angularVelOffset) * _t0;
        float _t3 = angularVel.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, angularVelOffset + 4L) * _t0;
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        return integrate_api_sc7afc19e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_sc7afc19e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        return (float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static float lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t6);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t6);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.pow_unsafe(_destBase, _srcBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return pow_api_se712b4c2_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t28, _t12 * (_t28 / _t26));
    }

    /** Piece 2 of {@code pow_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment pow_api_se712b4c2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t28, float _t31) {
        if (_t25 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t21 * _t31);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t22 * _t31);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t20 * _t31);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t28, _t26) * _t12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTowards_api_s99b40b59_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_s99b40b59_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
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
        return rotateTowards_api_s99b40b59_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_s99b40b59_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t73 * _t67);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t73 * _t68);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t73 * _t66);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t73 * _t65);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _targetBase, step);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return rotateTowards_api_s76e78aba_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_s76e78aba_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
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
        return rotateTowards_api_s76e78aba_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_s76e78aba_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t73 * _t67);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t73 * _t68);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t73 * _t66);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t73 * _t65);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_api_sbad11688_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_sbad11688_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
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
        return lookAlong_api_sbad11688_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_sbad11688_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        return lookAlong_api_sbad11688_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_sbad11688_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        return lookAlong_api_s22d8a986_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _upx, _upy, _upz, _t11, _t12, _t13, _t19, Math.fma(_t19, _t12, _upx), Math.fma(_t19, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t19, float _t20, float _t21) {
        float _t22 = Math.fma(_t19, _t11, _upz);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
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
        return lookAlong_api_s22d8a986_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t40, _t42, _t43, _t45, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t40, float _t42, float _t43, float _t45, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72) {
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        return lookAlong_api_s22d8a986_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t49, _t50, _t61, _t66, _t68, _t69, _t70, _t72, _t73, _t74, _t75, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 4 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2, float _sp3, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        return lookAlong_api_s22d8a986_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 5 of {@code lookAlong_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_api_s22d8a986_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, axisX * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, axisY * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, axisZ * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset) * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _axisy * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _axisz * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t49 = _t40 + _t12;
        float _t50 = _t12 - _t40;
        float _t61 = Math.fma(_t39, _t11, -(_t40 * _t12));
        float _t66 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13));
        float _t67 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13));
        float _t69 = Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41));
        float _t70 = Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41));
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t50);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t70);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t73));
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t69);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t49);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t69);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t50);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t49);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t49 = _t40 + _t12;
        float _t50 = _t12 - _t40;
        float _t61 = Math.fma(_t39, _t11, -(_t40 * _t12));
        float _t66 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13));
        float _t67 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13));
        float _t69 = Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41));
        float _t70 = Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41));
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t50);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t70);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t73));
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t69);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t49);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t69);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t50);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t49);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_api(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        return makeRotationTo_api_s952e01f1_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_s952e01f1_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        long _destBase = dest.address() + destOffset;
        long _fromDirBase = fromDir.address() + fromDirOffset;
        long _toDirBase = toDir.address() + toDirOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        float _fromDirx = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return makeRotationTo_api_s45fa7336_1(dest, destOffset, _t13, _t18, _t19, Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code makeRotationTo_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_s45fa7336_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 6.0E-8f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t9, _t7, _t12 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t10, _t5, _t11 * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t9, _t6, _t12 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t10, _t6, _t11 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t11, _t6, -(_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t12, _t6, -(_t9 * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t9, _t7, _t12 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t10, _t5, _t11 * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t12, _t5, -(_t9 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t10, _t8, _t11 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t11, _t8, -(_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t9, _t5, _t12 * _t8));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t2, -(_selfz * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t1, _selfz * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, _selfz * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t2, -(_selfx * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, -(_selfy * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t1, _selfy * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset) * _t1;
        float _t3 = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L) * _t1;
        float _t4 = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L) * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTo_api_sd42c0463_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_sd42c0463_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _fromDirBase = fromDir.address() + fromDirOffset;
        long _toDirBase = toDir.address() + toDirOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _fromDirx = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return rotateTo_api_s70774588_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)));
    }

    /** Piece 2 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_s70774588_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23) {
        float _t24 = 0.5f * _t23;
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        return rotateTo_api_s70774588_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t44, _t45, _t46, _t47);
    }

    /** Piece 3 of {@code rotateTo_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_s70774588_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t44, float _t45, float _t46, float _t47) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t2, _selfz * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t2, -(_selfy * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        return rotateXYZ_api_s9e29e86e_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_s9e29e86e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        return rotateXZY_api_s10a14270_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_s10a14270_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, -(_selfz * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t1, _selfz * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22)));
        return rotateYXZ_api_sdfa1152c_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_sdfa1152c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        return rotateYZX_api_s726c2dcc_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_s726c2dcc_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t2, _selfy * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t2, -(_selfx * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20)));
        return rotateZXY_api_sfe0b0494_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_sfe0b0494_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        return rotateZYX_api_s462c4512_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_s462c4512_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _t9 = 2.0f * Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _t9 = 2.0f * Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz))));
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
