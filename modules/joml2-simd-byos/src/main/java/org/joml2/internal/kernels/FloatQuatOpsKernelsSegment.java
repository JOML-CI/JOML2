// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.opssimd.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code FloatQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_selfx * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_selfy * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_selfz * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t3_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invert_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_selfx * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_selfy * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_selfz * _t3_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t3_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invertProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        float _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t23 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t22 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        float _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        float _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        float _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t23 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t22 * _t27_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t21 * _t27_inv);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        float _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        return invertProduct_api_fma_sf85da39d_1(dest, destOffset, _t21, _t22, _t23, _t27_inv);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_fma_sf85da39d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t21, float _t22, float _t23, float _t27_inv) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t23 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t22 * _t27_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        float _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        float _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        float _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t20 * _t27_inv));
        return invertProduct_api_mulAdd_s89cb024c_1(dest, destOffset, _t21, _t22, _t23, _t27_inv);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment invertProduct_api_mulAdd_s89cb024c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t21, float _t22, float _t23, float _t27_inv) {
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, otherX + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY + _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ + _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, otherW + _selfw);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery + _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz + _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _otherw + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, scalar * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, scalar * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, -_selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - otherY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - otherZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw - otherW);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - _othery);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - _otherz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw - _otherw);
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
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sinWithCos(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosWithSin(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinWithCos(_t3) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t3) * _t0);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _vw = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _vz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _vw);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m00 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset);
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m20 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 12L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m21 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m02 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m12 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 28L);
        float _m22 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 32L);
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
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m00 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m02 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m12 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m20 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _m21 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 36L);
        float _m22 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 40L);
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
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _m00 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset);
        float _m10 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 4L);
        float _m20 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 8L);
        float _m01 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 16L);
        float _m11 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 20L);
        float _m21 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 24L);
        float _m02 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 32L);
        float _m12 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 36L);
        float _m22 = m.get(_JAVA_FLOAT_UNALIGNED, mOffset + 40L);
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
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t1);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t9);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t4);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t6);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t4);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t7);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t6);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t8);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t9);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return toMatrix_api_fma_s918ae0b9_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2);
    }

    /** Piece 2 of {@code toMatrix_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix_api_fma_s918ae0b9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 40L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 60L, 1.0f);
        return dest;
    }

    /** {@code toMatrix_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        return toMatrix_api_mulAdd_s1bdec7b0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix_api_mulAdd_s1bdec7b0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 40L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 48L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 52L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 56L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 60L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix3x3_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix3x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x3_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        return toMatrix3x3_api_fma_se80b4f23_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x3_api_fma_se80b4f23_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    /** {@code toMatrix3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x3_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        return toMatrix3x3_api_mulAdd_sc8b4b1e2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x3_api_mulAdd_sc8b4b1e2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toMatrix3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_api_fma(dest, destOffset, src, srcOffset);
        return toMatrix3x4_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return toMatrix3x4_api_fma_s3f155e44_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2);
    }

    /** Piece 2 of {@code toMatrix3x4_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x4_api_fma_s3f155e44_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, 0.0f);
        return dest;
    }

    /** {@code toMatrix3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        return toMatrix3x4_api_mulAdd_s3a74403b_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x4_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment toMatrix3x4_api_mulAdd_s3a74403b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_fma(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return decomposeSwingTwist_api_fma_s64c32ef6_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_fma_s64c32ef6_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset, _t12);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 4L, _t13);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 8L, _t14);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 12L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_mulAdd(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_api_mulAdd_sc8b070ef_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_mulAdd_sc8b070ef_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, swingOffset + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_api_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return decomposeSwingTwist_api_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code decomposeSwingTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_fma(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        return decomposeSwingTwist_api_fma_s4c25a86b_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_fma_s4c25a86b_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset, _t12);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 4L, _t13);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 8L, _t14);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 12L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_mulAdd(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        return decomposeSwingTwist_api_mulAdd_sb01ad56a_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment decomposeSwingTwist_api_mulAdd_sb01ad56a_1(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        swing.set(_JAVA_FLOAT_UNALIGNED, swingOffset + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset, _t12);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 4L, _t13);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 8L, _t14);
        twist.set(_JAVA_FLOAT_UNALIGNED, twistOffset + 12L, _t11);
        return swing;
    }

    public static java.lang.foreign.MemorySegment getSwing_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getSwing_unsafe(_destBase, _srcBase, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getSwing_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_fma(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@code getSwing_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_api_fma_s986be055_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_fma_s986be055_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return getSwing_api_mulAdd_s859b7be2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_mulAdd_s859b7be2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset);
        return getSwing_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code getSwing_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        return getSwing_api_fma_s26ecccf0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_fma_s26ecccf0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return getSwing_api_mulAdd_s89da7537_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getSwing_api_mulAdd_s89da7537_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getTwist_unsafe(_destBase, _srcBase, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getTwist_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = java.lang.Math.fma(axisZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(axisX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), axisY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, axisX * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, axisY * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, axisZ * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getTwist_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = ((axisZ) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((axisX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (axisY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, axisX * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, axisY * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, axisZ * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset);
        return getTwist_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code getTwist_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getTwist_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = java.lang.Math.fma(_axisz, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(_axisx, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _axisy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _axisx * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _axisy * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _axisz * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getTwist_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = ((_axisz) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((_axisx) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_axisy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _axisx * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _axisy * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _axisz * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (otherW - _selfw) + (_selfw)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t5 = java.lang.Math.fma(alpha, targetW, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _t0);
        float _t6 = java.lang.Math.fma(alpha, targetZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _t0);
        float _t7 = java.lang.Math.fma(alpha, targetX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, targetY, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t7 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t8 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t6 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t5 * _t13);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t5 = ((alpha) * (targetW) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _t0));
        float _t6 = ((alpha) * (targetZ) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _t0));
        float _t7 = ((alpha) * (targetX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t0));
        float _t8 = ((alpha) * (targetY) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t7 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t8 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t6 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t5 * _t13);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code nlerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t5 = java.lang.Math.fma(alpha, target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _t0);
        float _t6 = java.lang.Math.fma(alpha, target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _t0);
        float _t7 = java.lang.Math.fma(alpha, target.get(_JAVA_FLOAT_UNALIGNED, targetOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        return nlerp_api_fma_s1f0f075f_1(dest, destOffset, _t5, _t6, _t7, _t8, _t12, (1.0f / (float) java.lang.Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code nlerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_fma_s1f0f075f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t5, float _t6, float _t7, float _t8, float _t12, float _t13) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t12 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t7 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t8 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t6 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t5 * _t13);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t5 = ((alpha) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * _t0));
        float _t6 = ((alpha) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _t0));
        float _t7 = ((alpha) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t0));
        float _t8 = ((alpha) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        return nlerp_api_mulAdd_scca47d10_1(dest, destOffset, _t5, _t6, _t7, _t8, _t12, (1.0f / (float) java.lang.Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code nlerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerp_api_mulAdd_scca47d10_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t5, float _t6, float _t7, float _t8, float _t12, float _t13) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t12 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t7 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t8 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t6 * _t13);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t5 * _t13);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return nlerpShortest_api_fma_s9b687a72_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_fma_s9b687a72_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t20 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t21 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t18 * _t26);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return nlerpShortest_api_mulAdd_s4da3a677_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_mulAdd_s4da3a677_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t20 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t21 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t18 * _t26);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code nlerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return nlerpShortest_api_fma_se2362af9_1(dest, destOffset, _t18, _t19, _t20, _t21);
    }

    /** Piece 2 of {@code nlerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_fma_se2362af9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t18, float _t19, float _t20, float _t21) {
        float _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t20 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t21 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t18 * _t26);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return nlerpShortest_api_mulAdd_s839ea09a_1(dest, destOffset, _t18, _t19, _t20, _t21);
    }

    /** Piece 2 of {@code nlerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment nlerpShortest_api_mulAdd_s839ea09a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t18, float _t19, float _t20, float _t21) {
        float _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t20 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t21 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t26);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t18 * _t26);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct7 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct7 > 2.0E-6f && _ct7 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct7;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        return slerp_api_fma_s113d5b67_1(dest, destOffset, targetY, targetZ, targetW, _selfy, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s113d5b67_1(java.lang.foreign.MemorySegment dest, long destOffset, float targetY, float targetZ, float targetW, float _selfy, float _selfz, float _selfw, float _t25, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct7 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct7 > 2.0E-6f && _ct7 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct7;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t25) + (targetX * _t26)));
        return slerp_api_mulAdd_s4572dd84_1(dest, destOffset, targetY, targetZ, targetW, _selfy, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s4572dd84_1(java.lang.foreign.MemorySegment dest, long destOffset, float targetY, float targetZ, float targetW, float _selfy, float _selfz, float _selfw, float _t25, float _t26) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t25) + (targetY * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t25) + (targetZ * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) {
                if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsSimd.slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
                return FloatQuatOpsSimd.slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
            }
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsSimd.slerp_degenerate_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return FloatQuatOpsSimd.slerp_degenerate_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t13) + (targetX * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t13) + (targetY * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t13) + (targetZ * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t13) + (targetW * _t14)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct7 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct7 > 2.0E-6f && _ct7 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_api_fma_s7a86dcaa_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t10, _t14, 2.0f * Math.sinWithCos(_t14) / _ct7);
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s7a86dcaa_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t10, float _t14, float _t22) {
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct7 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct7 > 2.0E-6f && _ct7 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_api_mulAdd_s9f1a1ccf_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t10, _t14, 2.0f * Math.sinWithCos(_t14) / _ct7);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s9f1a1ccf_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t10, float _t14, float _t22) {
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t25) + (_targetx * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t25) + (_targety * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t25) + (_targetz * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) {
                if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return FloatQuatOpsSimd.slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
                return FloatQuatOpsSimd.slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            }
            return FloatQuatOpsSimd.slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        return slerp_degenerate_api_fma_s3ee3ebf7_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t13, _t14);
    }

    /** Piece 2 of {@code slerp_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma_s3ee3ebf7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _selfw, float _targety, float _targetz, float _targetw, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t13) + (_targetx * _t14)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t13) + (_targety * _t14)));
        return slerp_degenerate_api_mulAdd_se1862728_1(dest, destOffset, _selfz, _selfw, _targetz, _targetw, _t13, _t14);
    }

    /** Piece 2 of {@code slerp_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd_se1862728_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _targetz, float _targetw, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t13) + (_targetz * _t14)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return slerpShortest_api_fma_s333375e5_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_fma_s333375e5_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t50 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t51 * _t45);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t51 * _t46);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t51 * _t44);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t51 * _t43);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return slerpShortest_api_mulAdd_s9a616fca_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_mulAdd_s9a616fca_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t50 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t51 * _t45);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t51 * _t46);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t51 * _t44);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t51 * _t43);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerpShortest_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return slerpShortest_api_fma_s63756d84_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_fma_s63756d84_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t50 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t51 * _t45);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t51 * _t46);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t51 * _t44);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t51 * _t43);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        return slerpShortest_api_mulAdd_sdbd9db41_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerpShortest_api_mulAdd_sdbd9db41_1(java.lang.foreign.MemorySegment dest, long destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t50 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t51 * _t45);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t51 * _t46);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t51 * _t44);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t51 * _t43);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        return squad_api_fma_s110a6b4d_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33)) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, t + t, _selfw + targetW, _selfz + targetZ, _selfx + targetX);
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_s110a6b4d_1(java.lang.foreign.MemorySegment dest, long destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10) {
        float _t11 = _selfy + targetY;
        float _t12 = _t0 * _t1;
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
        return squad_api_fma_s110a6b4d_2(dest, destOffset, control0Y, control1Y, targetX, targetY, targetZ, _selfx, _selfy, _selfz, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)), _t59, _t60, _t61, _t62, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60));
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_s110a6b4d_2(java.lang.foreign.MemorySegment dest, long destOffset, float control0Y, float control1Y, float targetX, float targetY, float targetZ, float _selfx, float _selfy, float _selfz, float _t12, float _t16, float _t59, float _t60, float _t61, float _t62, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = java.lang.Math.fma(_selfz, _t61, targetZ * _t62);
        float _t76 = java.lang.Math.fma(_selfx, _t61, targetX * _t62);
        float _t77 = java.lang.Math.fma(control0Y, _t59, control1Y * _t60);
        float _t78 = java.lang.Math.fma(_selfy, _t61, targetY * _t62);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        return squad_api_fma_s110a6b4d_3(dest, destOffset, _t72, _t73, _t74, _t75, _t77, _t78, _t105, _t106);
    }

    /** Piece 4 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_s110a6b4d_3(java.lang.foreign.MemorySegment dest, long destOffset, float _t72, float _t73, float _t74, float _t75, float _t77, float _t78, float _t105, float _t106) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        return squad_api_mulAdd_s5f1b10d2_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, t + t, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY);
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_s5f1b10d2_1(java.lang.foreign.MemorySegment dest, long destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11) {
        float _t12 = _t0 * _t1;
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
        return squad_api_mulAdd_s5f1b10d2_2(dest, destOffset, targetY, _selfy, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))), _t61, _t62, ((control0X) * (_t59) + (control1X * _t60)), ((control0W) * (_t59) + (control1W * _t60)), ((_selfw) * (_t61) + (targetW * _t62)), ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)));
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_s5f1b10d2_2(java.lang.foreign.MemorySegment dest, long destOffset, float targetY, float _selfy, float _t12, float _t16, float _t61, float _t62, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77) {
        float _t78 = ((_selfy) * (_t61) + (targetY * _t62));
        float _t79 = _t72 + _t73;
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t72) * (_t105) + (_t73 * _t106)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_api_fma(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return squad_api_mulAdd(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@code squad_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment control0, long control0Offset, java.lang.foreign.MemorySegment control1, long control1Offset, java.lang.foreign.MemorySegment target, long targetOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _control0x = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset);
        float _control0y = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 4L);
        float _control0z = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 8L);
        float _control0w = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 12L);
        float _control1x = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset);
        float _control1y = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 4L);
        float _control1z = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 8L);
        float _control1w = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        return squad_api_fma_sd1b9ce6c_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw);
    }

    /** Piece 2 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_sd1b9ce6c_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw) {
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = _control0w + _control1w;
        float _t4 = _control0z + _control1z;
        float _t5 = _control0x + _control1x;
        float _t6 = _control0y + _control1y;
        float _t8 = _selfw + _targetw;
        float _t9 = _selfz + _targetz;
        float _t10 = _selfx + _targetx;
        float _t11 = _selfy + _targety;
        float _t12 = _t0 * _t1;
        float _t23 = Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        float _t24 = Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        return squad_api_fma_sd1b9ce6c_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, Math.min(t, _t0), _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)), _t23, _t24, 4.0f - _t23, 4.0f - _t24);
    }

    /** Piece 3 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_sd1b9ce6c_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t7, float _t12, float _t16, float _t23, float _t24, float _t27, float _t28) {
        float _t33 = _t7 * quatArcAngle_fma(_t23);
        float _t34 = _t7 * quatArcAngle_fma(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
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
        return squad_api_fma_sd1b9ce6c_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _control0y, _control0z, _control1y, _control1z, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t59, _t60, _t61, _t62, java.lang.Math.fma(_control0x, _t59, _control1x * _t60), java.lang.Math.fma(_control0w, _t59, _control1w * _t60));
    }

    /** Piece 4 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_sd1b9ce6c_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _control0y, float _control0z, float _control1y, float _control1z, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t59, float _t60, float _t61, float _t62, float _t71, float _t72) {
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
        return squad_api_fma_sd1b9ce6c_4(dest, destOffset, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t105, _t106);
    }

    /** Piece 5 of {@code squad_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_fma_sd1b9ce6c_4(java.lang.foreign.MemorySegment dest, long destOffset, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t105, float _t106) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment control0, long control0Offset, java.lang.foreign.MemorySegment control1, long control1Offset, java.lang.foreign.MemorySegment target, long targetOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _control0x = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset);
        float _control0y = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 4L);
        float _control0z = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 8L);
        float _control0w = control0.get(_JAVA_FLOAT_UNALIGNED, control0Offset + 12L);
        float _control1x = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset);
        float _control1y = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 4L);
        float _control1z = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 8L);
        float _control1w = control1.get(_JAVA_FLOAT_UNALIGNED, control1Offset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        return squad_api_mulAdd_sa64482d1_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw);
    }

    /** Piece 2 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_sa64482d1_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw) {
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = _control0w + _control1w;
        float _t4 = _control0z + _control1z;
        float _t5 = _control0x + _control1x;
        float _t6 = _control0y + _control1y;
        float _t8 = _selfw + _targetw;
        float _t9 = _selfz + _targetz;
        float _t10 = _selfx + _targetx;
        float _t11 = _selfy + _targety;
        float _t12 = _t0 * _t1;
        float _t23 = Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        float _t24 = Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        return squad_api_mulAdd_sa64482d1_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, Math.min(t, _t0), _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))), _t23, _t24, 4.0f - _t23, 4.0f - _t24);
    }

    /** Piece 3 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_sa64482d1_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t7, float _t12, float _t16, float _t23, float _t24, float _t27, float _t28) {
        float _t33 = _t7 * quatArcAngle_mulAdd(_t23);
        float _t34 = _t7 * quatArcAngle_mulAdd(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
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
        return squad_api_mulAdd_sa64482d1_3(dest, destOffset, _selfx, _selfy, _selfz, _control0y, _control0z, _control1y, _control1z, _targetx, _targety, _targetz, _t12, _t16, _t59, _t60, _t61, _t62, ((_control0x) * (_t59) + (_control1x * _t60)), ((_control0w) * (_t59) + (_control1w * _t60)), ((_selfw) * (_t61) + (_targetw * _t62)));
    }

    /** Piece 4 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_sa64482d1_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _control0y, float _control0z, float _control1y, float _control1z, float _targetx, float _targety, float _targetz, float _t12, float _t16, float _t59, float _t60, float _t61, float _t62, float _t71, float _t72, float _t73) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        return squad_api_mulAdd_sa64482d1_4(dest, destOffset, _t72, _t73, _t74, _t75, _t77, _t78, _t105, _t106);
    }

    /** Piece 5 of {@code squad_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment squad_api_mulAdd_sa64482d1_4(java.lang.foreign.MemorySegment dest, long destOffset, float _t72, float _t73, float _t74, float _t75, float _t77, float _t78, float _t105, float _t106) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return mul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code mul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment mul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(weight, otherX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(weight, otherY, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(weight, otherZ, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(weight, otherW, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((weight) * (otherX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((weight) * (otherY) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((weight) * (otherZ) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((weight) * (otherW) + (_selfw)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(weight, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(weight, _othery, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(weight, _otherz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((weight) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((weight) * (_othery) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((weight) * (_otherz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((weight) * (_otherw) + (_selfw)));
        return dest;
    }

    public static float angle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static float angle_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
        }
    }

    public static float angleTo_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
    private static float angleTo_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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

    public static float angleTo_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static float angleTo_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_api_fma(src, srcOffset, other, otherOffset);
        return angleTo_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
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
        return angleTo_api_fma_s30e7724d_1(_selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _selfw + _t9, _selfz + _t10, _selfx + _t11, _selfy + _t12);
    }

    /** Piece 2 of {@code angleTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleTo_api_fma_s30e7724d_1(float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
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
        return angleTo_api_mulAdd_s2a4d1204_1(_selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _selfw + _t9, _selfz + _t10, _selfx + _t11, _selfy + _t12);
    }

    /** Piece 2 of {@code angleTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleTo_api_mulAdd_s2a4d1204_1(float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static java.lang.foreign.MemorySegment axis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment axis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return axis_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code axis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment axis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f))))));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment conjugate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment conjugateBy_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        float _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        return conjugateBy_api_fma_s338f9446_1(dest, destOffset, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_fma_s338f9446_1(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        float _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        float _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        float _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        return conjugateBy_api_mulAdd_sc53f16ed_1(dest, destOffset, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_mulAdd_sc53f16ed_1(java.lang.foreign.MemorySegment dest, long destOffset, float qX, float qY, float qZ, float qW, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_api_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return conjugateBy_api_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@code conjugateBy_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _qx = q.get(_JAVA_FLOAT_UNALIGNED, qOffset);
        float _qy = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qz = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qw = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        float _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        return conjugateBy_api_fma_s6bb9cb8d_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_fma_s6bb9cb8d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _qx = q.get(_JAVA_FLOAT_UNALIGNED, qOffset);
        float _qy = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qz = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qw = q.get(_JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        float _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        float _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        float _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        return conjugateBy_api_mulAdd_sacd0503c_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment conjugateBy_api_mulAdd_sacd0503c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment difference_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return difference_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return difference_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code difference_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        return difference_api_fma_s24fd22bb_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t3_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code difference_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_fma_s24fd22bb_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t3_inv, float _sp1, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        return difference_api_mulAdd_s7d56413a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t3_inv, _sp1, _sp0);
    }

    /** Piece 2 of {@code difference_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment difference_api_mulAdd_s7d56413a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t3_inv, float _sp1, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), java.lang.Math.fma(otherZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(otherX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        } else {
            return ((otherW) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) + (((otherZ) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((otherX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (otherY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))));
        }
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), java.lang.Math.fma(other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(other.get(_JAVA_FLOAT_UNALIGNED, otherOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
        } else {
            return ((other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) + (((other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))))));
        }
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.min(Math.exp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t10);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t10);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t10);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment exp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.min(Math.exp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t10);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t10);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t10);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesXYZ_api_fma_s2b93984b_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2), 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1), _t12, _t13, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_fma_s2b93984b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t11, _t14));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t12, _t13));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesXYZ_api_mulAdd_s3bfa9e4a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * ((_selfx) * (_selfz) + (_t2)), 2.0f * ((_selfx) * (_selfw) + (_t1)), _t12, _t13, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))), ((_t13) * (_t13) + (_t12 * _t12)));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd_s3bfa9e4a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t11, _t14));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t12, _t13));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesXYZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesXYZ_api_fma_sdc8701b1_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2), 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1), _t12, _t13, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_fma_sdc8701b1_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t11, _t14));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t12, _t13));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesXYZ_api_mulAdd_sb1d4d786_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * ((_selfx) * (_selfz) + (_t2)), 2.0f * ((_selfx) * (_selfw) + (_t1)), _t12, _t13, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))), ((_t13) * (_t13) + (_t12 * _t12)));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXYZ_api_mulAdd_sb1d4d786_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t11, _t14));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t12, _t13));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        return getEulerAnglesXZY_api_fma_sb5ec3eb5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_fma_sb5ec3eb5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        return getEulerAnglesXZY_api_mulAdd_s7d77caac_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd_s7d77caac_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesXZY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        return getEulerAnglesXZY_api_fma_sa900d3b_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_fma_sa900d3b_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        return getEulerAnglesXZY_api_mulAdd_s2e388170_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesXZY_api_mulAdd_s2e388170_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_api_fma_sc5a70a01_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_fma_sc5a70a01_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesYXZ_api_mulAdd_s8d1c44b8_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * ((_selfx) * (_selfw) - (_t0)), _t11, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd_s8d1c44b8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesYXZ_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_api_fma_se9d76b67_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_fma_se9d76b67_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesYXZ_api_mulAdd_s97825f6c_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * ((_selfx) * (_selfw) - (_t0)), _t11, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYXZ_api_mulAdd_s97825f6c_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, _t11));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfz * _selfw;
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        return getEulerAnglesYZX_api_fma_s48ca369_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2), 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t13, _t14, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t14, _t14, _t13 * _t13));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_fma_s48ca369_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t11, float _t12, float _t13, float _t14, float _t15, float _t17) {
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t12, _t15));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t13, _t14));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfz * _selfw;
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        return getEulerAnglesYZX_api_mulAdd_s73df7fe0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * ((_selfx) * (_selfy) + (_t2)), 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)), _t13, _t14, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))), ((_t14) * (_t14) + (_t13 * _t13)));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd_s73df7fe0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t11, float _t12, float _t13, float _t14, float _t15, float _t17) {
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t12, _t15));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t13, _t14));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesYZX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfz * _selfw;
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        return getEulerAnglesYZX_api_fma_s4bf37cef_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2), 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t13, _t14, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t14, _t14, _t13 * _t13));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_fma_s4bf37cef_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t11, float _t12, float _t13, float _t14, float _t15, float _t17) {
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t12, _t15));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t13, _t14));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = _selfz * _selfw;
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        return getEulerAnglesYZX_api_mulAdd_sab7a2034_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0f * ((_selfx) * (_selfy) + (_t2)), 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)), _t13, _t14, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))), ((_t14) * (_t14) + (_t13 * _t13)));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesYZX_api_mulAdd_sab7a2034_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t2, float _t11, float _t12, float _t13, float _t14, float _t15, float _t17) {
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t12, _t15));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t13, _t14));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfz * _selfz;
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        return getEulerAnglesZXY_api_fma_s75eb7569_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t12, _t13, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_fma_s75eb7569_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, _t14));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t12, _t13));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfz * _selfz;
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        return getEulerAnglesZXY_api_mulAdd_sa37d05e0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)), 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)), _t12, _t13, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1)))), ((_t13) * (_t13) + (_t12 * _t12)));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd_sa37d05e0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, _t14));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t12, _t13));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesZXY_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfz * _selfz;
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        return getEulerAnglesZXY_api_fma_s9bba22ef_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t12, _t13, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_fma_s9bba22ef_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, _t14));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t12, _t13));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = _selfz * _selfz;
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        return getEulerAnglesZXY_api_mulAdd_s14bec234_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)), 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)), _t12, _t13, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1)))), ((_t13) * (_t13) + (_t12 * _t12)));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZXY_api_mulAdd_s14bec234_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t11, float _t12, float _t13, float _t14, float _t16) {
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t11, _t14));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t12, _t13));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesZYX_api_fma_sa23d4767_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_fma_sa23d4767_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, _t10));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        return getEulerAnglesZYX_api_mulAdd_s5a2ad316_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t10, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))), ((_t10) * (_t10) + (_t8 * _t8)));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd_s5a2ad316_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, _t10));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(_destBase, _srcBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment getEulerAnglesZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_api_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesZYX_api_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesZYX_api_fma_sf38b6a7d_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_fma_sf38b6a7d_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, _t10));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        return getEulerAnglesZYX_api_mulAdd_scc49c822_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t10, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))), ((_t10) * (_t10) + (_t8 * _t8)));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment getEulerAnglesZYX_api_mulAdd_scc49c822_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_t8, _t10));
        }
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.lang.foreign.MemorySegment integrate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_api_fma_s9c078920_1(dest, destOffset, _t9, _selfx, _selfy, _selfz, _selfw, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_fma_s9c078920_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t9, float _selfx, float _selfy, float _selfz, float _selfw, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        return integrate_api_mulAdd_sa08eec19_1(dest, destOffset, _t9, _selfx, _selfy, _selfz, _selfw, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_mulAdd_sa08eec19_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t9, float _selfx, float _selfy, float _selfz, float _selfw, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_api_fma(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return integrate_api_mulAdd(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@code integrate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment angularVel, long angularVelOffset, float dt) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset + 8L) * _t0;
        float _t2 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset) * _t0;
        float _t3 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset + 4L) * _t0;
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        return integrate_api_fma_s56192791_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_fma_s56192791_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment angularVel, long angularVelOffset, float dt) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset + 8L) * _t0;
        float _t2 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset) * _t0;
        float _t3 = angularVel.get(_JAVA_FLOAT_UNALIGNED, angularVelOffset + 4L) * _t0;
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        return integrate_api_mulAdd_s6a0af35c_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment integrate_api_mulAdd_s6a0af35c_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNegativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNegativeX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNegativeY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNegativeZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNormalizedNegativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    /** {@code invNormalizedNegativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNormalizedNegativeX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNormalizedNegativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    /** {@code invNormalizedNegativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNormalizedNegativeY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedNegativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNormalizedNegativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    /** {@code invNormalizedNegativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invNormalizedNegativeZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invNormalizedPositiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment invPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment invPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invPositiveX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invPositiveY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment invPositiveZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return (float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static float lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t6);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment log_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t6);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t6);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negativeX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment negativeX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t10 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment negativeY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t12 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t9 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment negativeZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -(_t9 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -(_t10 * _t16));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -(_t12 * _t16));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalize_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizedNegativeX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    /** {@code normalizedNegativeX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizedNegativeX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizedNegativeY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    /** {@code normalizedNegativeY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizedNegativeY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedNegativeZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizedNegativeZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    /** {@code normalizedNegativeZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizedNegativeZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizedPositiveZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment positiveX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment positiveX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment positiveX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t10 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment positiveY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t12 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t9 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment positiveZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t9 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t10 * _t16);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t12 * _t16);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_api_fma(dest, destOffset, src, srcOffset, t);
        return pow_api_mulAdd(dest, destOffset, src, srcOffset, t);
    }

    /** {@code pow_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment pow_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return pow_api_fma_sce4f36e1_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment pow_api_fma_sce4f36e1_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t21 * _t31);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t22 * _t31);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t20 * _t31);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment pow_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return pow_api_mulAdd_s43c2cc52_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment pow_api_mulAdd_s43c2cc52_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t25 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t21 * _t31);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t22 * _t31);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t20 * _t31);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTowards_api_fma_s4182b9dc_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37, _t38, _t37 * _t38);
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4182b9dc_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37, float _t38, float _t43) {
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
        return rotateTowards_api_fma_s4182b9dc_2(dest, destOffset, _t79, _t80, _t81, _t82);
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4182b9dc_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t79, float _t80, float _t81, float _t82) {
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t86 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t87 * _t81);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t87 * _t82);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t87 * _t80);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t87 * _t79);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTowards_api_mulAdd_s323c4aaf_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37)));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s323c4aaf_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46) {
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
        float _t86 = ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82))))));
        return rotateTowards_api_mulAdd_s323c4aaf_2(dest, destOffset, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s323c4aaf_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t86 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t87 * _t81);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t87 * _t82);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t87 * _t80);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t87 * _t79);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_api_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_api_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@code rotateTowards_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        return rotateTowards_api_fma_s4bf19091_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _selfw + _t9, _selfz + _t10, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, java.lang.Math.fma(_t15, _t15, _t16 * _t16));
    }

    /** Piece 2 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4bf19091_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30) {
        float _t37 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30));
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        return rotateTowards_api_fma_s4bf19091_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t43, _t55, 1.0f - _t55, _t65, java.lang.Math.fma(-0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -_t19 * _t19) + java.lang.Math.fma(-_t17, _t17, -_t18 * _t18))), _t65, Math.cosWithSin(_t61)));
    }

    /** Piece 3 of {@code rotateTowards_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_fma_s4bf19091_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t43, float _t55, float _t56, float _t65, float _t74) {
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
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t86 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t87 * _t81);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t87 * _t82);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t87 * _t80);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t87 * _t79);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
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
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        return rotateTowards_api_mulAdd_s438c58bc_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30)))));
    }

    /** Piece 2 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s438c58bc_1(java.lang.foreign.MemorySegment dest, long destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t43 = _t37 * _t38;
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
        return rotateTowards_api_mulAdd_s438c58bc_2(dest, destOffset, _t79, _t80, _t81, _t82);
    }

    /** Piece 3 of {@code rotateTowards_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_api_mulAdd_s438c58bc_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t79, float _t80, float _t81, float _t82) {
        float _t86 = ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82))))));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t86 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t87 * _t81);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t87 * _t82);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t87 * _t80);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t87 * _t79);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_fma_s5cdc5700_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s5cdc5700_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_fma_s5cdc5700_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s5cdc5700_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_s5cdc5700_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s5cdc5700_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_mulAdd_s97dd42dd_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s97dd42dd_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_mulAdd_s97dd42dd_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s97dd42dd_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s97dd42dd_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s97dd42dd_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_fma_s83699ae6_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s83699ae6_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_fma_s83699ae6_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s83699ae6_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_api_fma_s83699ae6_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s83699ae6_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_mulAdd_s1c67d41b_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s1c67d41b_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_mulAdd_s1c67d41b_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s1c67d41b_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s1c67d41b_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s1c67d41b_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_fma_s10d093be_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t18, java.lang.Math.fma(_t18, _t12, _upx), java.lang.Math.fma(_t18, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
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
        return lookAlong_lh_api_fma_s10d093be_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t44, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t44, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71) {
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return lookAlong_lh_api_fma_s10d093be_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)));
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_s10d093be_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 5 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_s10d093be_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_mulAdd_s6c5cea49_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t18) * (_t12) + (_upx)), ((_t18) * (_t13) + (_upy)), ((_t18) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s6c5cea49_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t19, float _t20, float _t21) {
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
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
        return lookAlong_lh_api_mulAdd_s6c5cea49_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11)))));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s6c5cea49_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73) {
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
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
        return lookAlong_lh_api_mulAdd_s6c5cea49_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s6c5cea49_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_fma_sda371304_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t20, java.lang.Math.fma(_t20, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_sda371304_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t20, float _t21) {
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
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
        return lookAlong_rh_api_fma_sda371304_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, 1.0f + _t41, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_sda371304_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t46, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_api_fma_sda371304_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_sda371304_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_api_fma_sda371304_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_sda371304_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_mulAdd_s8f09fa97_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t20, ((_t20) * (_t13) + (_upy)), ((_t20) * (_t12) + (_upx)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s8f09fa97_1(java.lang.foreign.MemorySegment dest, long destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t20, float _t21, float _t22) {
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
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
        return lookAlong_rh_api_mulAdd_s8f09fa97_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s8f09fa97_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74) {
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_api_mulAdd_s8f09fa97_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s8f09fa97_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_s8f09fa97_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_s8f09fa97_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_fma_sbdcd15d4_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sbdcd15d4_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_fma_sbdcd15d4_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sbdcd15d4_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_api_fma_sbdcd15d4_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sbdcd15d4_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_mulAdd_s3331d9c3_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s3331d9c3_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
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
        return lookAlong_lh_api_mulAdd_s3331d9c3_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s3331d9c3_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_api_mulAdd_s3331d9c3_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s3331d9c3_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_fma_s81374f32_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s81374f32_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_fma_s81374f32_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s81374f32_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_api_fma_s81374f32_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s81374f32_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_mulAdd_sa6d6d841_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_sa6d6d841_1(java.lang.foreign.MemorySegment dest, long destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
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
        return lookAlong_rh_api_mulAdd_sa6d6d841_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_sa6d6d841_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_sa6d6d841_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_sa6d6d841_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_fma_sf4e8934a_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t18, java.lang.Math.fma(_t18, _t12, _upx), java.lang.Math.fma(_t18, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
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
        return lookAlong_lh_api_fma_sf4e8934a_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t44, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t44, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71) {
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return lookAlong_lh_api_fma_sf4e8934a_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)));
    }

    /** Piece 4 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_api_fma_sf4e8934a_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 5 of {@code lookAlong_lh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_fma_sf4e8934a_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_lh_api_mulAdd_s55c70dcf_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t18) * (_t12) + (_upx)), ((_t18) * (_t13) + (_upy)), ((_t18) * (_t11) + (_upz)));
    }

    /** Piece 2 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s55c70dcf_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t19, float _t20, float _t21) {
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
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
        return lookAlong_lh_api_mulAdd_s55c70dcf_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11)))));
    }

    /** Piece 3 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s55c70dcf_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73) {
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
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
        return lookAlong_lh_api_mulAdd_s55c70dcf_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_lh_api_mulAdd_s55c70dcf_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_api_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_api_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_fma_s87226148_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t20, java.lang.Math.fma(_t20, _t13, _upy));
    }

    /** Piece 2 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s87226148_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t20, float _t21) {
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
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
        return lookAlong_rh_api_fma_s87226148_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, 1.0f + _t41, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)));
    }

    /** Piece 3 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s87226148_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t46, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_api_fma_s87226148_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s87226148_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_api_fma_s87226148_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_fma_s87226148_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return lookAlong_rh_api_mulAdd_scf41b4ed_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t20, ((_t20) * (_t13) + (_upy)), ((_t20) * (_t12) + (_upx)));
    }

    /** Piece 2 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_scf41b4ed_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t20, float _t21, float _t22) {
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
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
        return lookAlong_rh_api_mulAdd_scf41b4ed_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))));
    }

    /** Piece 3 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_scf41b4ed_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74) {
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_api_mulAdd_scf41b4ed_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_scf41b4ed_3(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_api_mulAdd_scf41b4ed_4(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_rh_api_mulAdd_scf41b4ed_4(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, axisX * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, axisY * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, axisZ * _t1);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _axisBase = axis.address() + axisOffset;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset) * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _axisy * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _axisz * _t1);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t14 = -_t13;
        float _t15 = -_t11;
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
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t14 = -_t13;
        float _t15 = -_t11;
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
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
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
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
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
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t14 = -_t13;
        float _t15 = -_t11;
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
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        float _t14 = -_t13;
        float _t15 = -_t11;
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
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_lh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t65);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t49);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t69);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t68);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t48);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t49);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t48);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t66);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_api_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_api_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
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
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationLookAlong_rh_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _dirx = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _dirz = dir.get(_JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _upx = up.get(_JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _upz = up.get(_JAVA_FLOAT_UNALIGNED, upOffset + 8L);
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
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
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
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp0 * _t67);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp0 * _t51);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp0 * _t71);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp3 * _t70);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp3 * _t50);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp1 * _t70);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _sp1 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp1 * _t51);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _sp2 * _t50);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _sp2 * _t68);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _sp2 * _t71);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        return makeRotationTo_api_fma_sb8456d72_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma_sb8456d72_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > 6.0E-8f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        return makeRotationTo_api_mulAdd_sa0fa0207_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd_sa0fa0207_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > 6.0E-8f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return makeRotationTo_api_fma_s86d340bd_1(dest, destOffset, _t13, _t18, _t19, java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma_s86d340bd_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > 6.0E-8f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return makeRotationTo_api_mulAdd_s801841f4_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd_s801841f4_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > 6.0E-8f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_api(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        return makeRotationTo_api_fma_s337cc7ee_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma_s337cc7ee_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > epsilon) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        return makeRotationTo_api_mulAdd_s1847071d_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd_s1847071d_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > epsilon) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _fromDirBase = fromDir.address() + fromDirOffset;
        long _toDirBase = toDir.address() + toDirOffset;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_api_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return makeRotationTo_api_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code makeRotationTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return makeRotationTo_api_fma_s825dd023_1(dest, destOffset, epsilon, _t13, _t18, _t19, java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code makeRotationTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_fma_s825dd023_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t32 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17)))));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > epsilon) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
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
        return makeRotationTo_api_mulAdd_s26cec838_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationTo_api_mulAdd_s26cec838_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t24 > epsilon) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t15 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t17 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t16 * _t32);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t30 * _t13);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t30 * _t19);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t30 * _t18);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sinWithCos(_t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinWithCos(_t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t12) * (_t5) - (_t11 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZ_api(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinWithCos(_t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment makeRotationZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t11) * (_t8) + (_t12 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t12) * (_t8) - (_t11 * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code preRotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) - (_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t2) + (_selfz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code preRotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t1) - (_selfx * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateZ_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code preRotateZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) - (_selfy * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t2) + (_selfy * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset) * _t2;
        float _t4 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L) * _t2;
        float _t5 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L) * _t2;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset) * _t2;
        float _t4 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L) * _t2;
        float _t5 = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L) * _t2;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_s28f8fa92_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s28f8fa92_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_s66e73283_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd_s66e73283_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTo_api_fma_s6e838c5d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s6e838c5d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23) {
        float _t24 = 0.5f * _t23;
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        return rotateTo_api_fma_s6e838c5d_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t44, _t45, _t46, _t47);
    }

    /** Piece 3 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s6e838c5d_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t44, float _t45, float _t46, float _t47) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTo_api_mulAdd_sb4fe97a8_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23);
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd_sb4fe97a8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24) {
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_api_fma_s9f009e0e_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s9f009e0e_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
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
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_api_mulAdd_se900a3a9_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd_se900a3a9_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _fromDirBase = fromDir.address() + fromDirOffset;
        long _toDirBase = toDir.address() + toDirOffset;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTo_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_api_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return rotateTo_api_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code rotateTo_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTo_api_fma_s3477a43_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6)));
    }

    /** Piece 2 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s3477a43_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23) {
        float _t24 = 0.5f * _t23;
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        return rotateTo_api_fma_s3477a43_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t44, _t45, _t46, _t47);
    }

    /** Piece 3 of {@code rotateTo_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_fma_s3477a43_2(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t44, float _t45, float _t46, float _t47) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _fromDirx = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset);
        float _fromDiry = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 4L);
        float _fromDirz = fromDir.get(_JAVA_FLOAT_UNALIGNED, fromDirOffset + 8L);
        float _toDirx = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset);
        float _toDiry = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 4L);
        float _toDirz = toDir.get(_JAVA_FLOAT_UNALIGNED, toDirOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        return rotateTo_api_mulAdd_s8f10cbdc_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23);
    }

    /** Piece 2 of {@code rotateTo_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTo_api_mulAdd_s8f10cbdc_1(java.lang.foreign.MemorySegment dest, long destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24) {
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) + (_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t1) - (_selfy * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_api_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        return rotateXYZ_api_fma_sc2339cc7_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_fma_sc2339cc7_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXYZ_api_mulAdd_s39d68ffe_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXYZ_api_mulAdd_s39d68ffe_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXZY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_api_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_api_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        return rotateXZY_api_fma_s52ae3281_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_fma_s52ae3281_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXZY_api_mulAdd_s74d50030_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXZY_api_mulAdd_s74d50030_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) - (_selfz * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t2) + (_selfz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_api_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        return rotateYXZ_api_fma_s206fa85d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_fma_s206fa85d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        return rotateYXZ_api_mulAdd_sa96a4214_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYXZ_api_mulAdd_sa96a4214_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYZX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_api_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_api_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        return rotateYZX_api_fma_s4f01f7f5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_fma_s4f01f7f5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateYZX_api_mulAdd_s8d04c974_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYZX_api_mulAdd_s8d04c974_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZ_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code rotateZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t1) + (_selfy * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t1) - (_selfx * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t1) + (_selfw * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZXY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_api_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        return rotateZXY_api_fma_s326912a5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_fma_s326912a5_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        return rotateZXY_api_mulAdd_s550b58bc_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZXY_api_mulAdd_s550b58bc_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZYX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_api_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_api_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        return rotateZYX_api_fma_sc38897a3_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_fma_sc38897a3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateZYX_api_mulAdd_s842a822a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZYX_api_mulAdd_s842a822a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transform_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transform_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (vY) - (_selfy * vX));
        float _t10 = 2.0f * ((_selfz) * (vX) - (_selfx * vZ));
        float _t11 = 2.0f * ((_selfy) * (vZ) - (_selfz * vY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transform_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_vy) - (_selfy * _vx));
        float _t10 = 2.0f * ((_selfz) * (_vx) - (_selfx * _vz));
        float _t11 = 2.0f * ((_selfy) * (_vz) - (_selfz * _vy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (vZ) - (_selfz * vX));
        float _t10 = 2.0f * ((_selfy) * (vX) - (_selfx * vY));
        float _t11 = 2.0f * ((_selfz) * (vY) - (_selfy * vZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment transformInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((_selfx) * (_vz) - (_selfz * _vx));
        float _t10 = 2.0f * ((_selfy) * (_vx) - (_selfx * _vy));
        float _t11 = 2.0f * ((_selfz) * (_vy) - (_selfy * _vz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
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
