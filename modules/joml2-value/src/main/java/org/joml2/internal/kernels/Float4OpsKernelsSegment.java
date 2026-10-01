// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float4Ops} and its sibling kernel units. Not public API.
 */
public final class Float4OpsKernelsSegment {
    private Float4OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
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
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / otherZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / _otherz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, b, cZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, b, _cz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), bX, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, bY, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, bZ, cZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _by, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _bz, _cz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
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

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, otherW * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _otherw * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
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
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
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
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v, float w) {
        long _destBase = dest.address() + destOffset;
        Float4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u);
        float _t6 = Math.sin(_t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosFromSin(_t4, _t1) * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4 * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosFromSin(_t6, _t3) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _t6 * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = dest.address() + destOffset;
        Float4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
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
        Float4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
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

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1X, _t7, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        return bezier_api_s74d67654_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0f * _t0 * _t1, 3.0f * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_s74d67654_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t7, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t6, p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t0, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return bezier2_api_sd4a3462f_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_sd4a3462f_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t0, float _t3, float _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return bezier2Tangent_api_s1329f74a_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_s1329f74a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t1 = 1.0f - t;
        return bezierTangent_api_s72e1eb9b_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0f * t * t, 6.0f * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_s72e1eb9b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t1, float _t2, float _t5) {
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_s57ba87ea_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s57ba87ea_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (Math.fma(2.0f, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), _t0, Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRom_api_s45be6251_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s45be6251_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_api_s45be6251_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s45be6251_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), _t0, Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_api_s43008f69_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_s43008f69_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), Math.fma(3.0f * Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRomTangent_api_sdfa329c8_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_sdfa329c8_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_api_sdfa329c8_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_sdfa329c8_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), Math.fma(3.0f * Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        float _t0 = t * t;
        return hermite_api_s1a309e55_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * Math.fma(t, t, -t), Math.fma(t - 2.0f, _t0, t));
    }

    /** Piece 2 of {@code hermite_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_s1a309e55_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t2, float _t5, float _t7) {
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t7) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t5, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        return hermiteTangent_api_s7d4d76b8_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, t * t, 6.0f * Math.fma(t, t, -t), 6.0f * Math.fma(-t, t, t));
    }

    /** Piece 2 of {@code hermiteTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_s7d4d76b8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t6, float _t7) {
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t9) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t8, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
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
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
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

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(tY, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _tBase = t.address() + tOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _ty = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        float _tw = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_sbd9832fc_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_sbd9832fc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_sbd9832fc_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)), _t28 * Math.cos(_t61));
    }

    /** Piece 3 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_sbd9832fc_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _sp0, float _t66) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_s3237ebe1_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0f / (float) java.lang.Math.sqrt(_t9)), (1.0f / (float) java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s3237ebe1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t9, float _t10, float _t11, float _t14) {
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        return slerp_api_s3237ebe1_2(dest, destOffset, src, srcOffset, other, otherOffset, t, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s3237ebe1_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t49, float _t50, float _t51, float _t52, float _t57) {
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t7 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = java.lang.Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * Math.sin(_t110);
        float _t115 = _t60 * Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.abs(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.acos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.acos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.acos(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.acos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, bX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, bY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(scalar, bZ, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, _by, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(scalar, _bz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(bX, cX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(bY, cY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(bZ, cZ, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_by, _cy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_bz, _cz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        float _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        float _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        float _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        return angleBetween_degenerate_api_sf9e40f65_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_sf9e40f65_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41) {
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        float _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        float _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        float _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        return angleBetween_api_s789d8bf3_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code angleBetween_api}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_s789d8bf3_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t6 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        return angleBetween_degenerate_api_sf89d9cc8_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_sf89d9cc8_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t36, float _t37, float _t38, float _t39) {
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.asin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.asin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.asin(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.asin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ, float xW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ, float xW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), xX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, xY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, xZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _xBase = x.address() + xOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _xy = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 4L);
        float _xz = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 8L);
        float _xw = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, _xy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, _xz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cbrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cbrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cbrt(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cbrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ceil(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ceil(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(java.lang.Math.max(_selfw, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), minX), maxX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(java.lang.Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _minBase = min.address() + minOffset;
        long _maxBase = max.address() + maxOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _miny = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 4L);
        float _minz = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 8L);
        float _minw = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 12L);
        float _maxy = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 4L);
        float _maxz = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 8L);
        float _maxw = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset)), max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(java.lang.Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static float compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static float compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static float compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ, float signW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ, float signW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), signX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, signY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, signZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _signBase = sign.address() + signOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _signy = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 4L);
        float _signz = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 8L);
        float _signw = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, _signy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, _signz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cos(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        long _wBase = w.address() + wOffset;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _vw = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        float _wx = w.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, wOffset);
        float _wy = w.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, wOffset + 4L);
        float _wz = w.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, wOffset + 8L);
        float _ww = w.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, wOffset + 12L);
        return cross_api_s785e5798_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, Math.fma(_vy, _wz, -(_vz * _wy)), Math.fma(_vz, _ww, -(_vw * _wz)), Math.fma(_vy, _ww, -(_vw * _wy)), Math.fma(_vx, _wz, -(_vz * _wx)), Math.fma(_vx, _ww, -(_vw * _wx)), Math.fma(_vx, _wy, -(_vy * _wx)));
    }

    /** Piece 2 of {@code cross_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_s785e5798_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toDegrees(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toDegrees(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(otherZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(otherX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))));
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.exp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.exp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.exp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(2.0f, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(2.0f, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(2.0f, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(2.0f, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.expm1(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.expm1(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.expm1(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.expm1(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 12L), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 12L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 8L), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 8L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 4L)))) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.floor(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.floor(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.floor(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.floor(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(_selfw - Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, yZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        float _yw = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, _yz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 1.0f / src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 1.0f / _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 1.0f / _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f / _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (1.0f / (float) java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.length_unsafe(_srcBase);
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
        return Float4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
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
        Float4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log10(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log10(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log10(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log10(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log1p(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log1p(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log1p(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log1p(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log2(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log2(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW);
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L));
    }

    public static float manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.max(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, otherZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.max(_selfw, otherW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, _otherz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, otherZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(_selfw, otherW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, _otherz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, yZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _yx = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        float _yw = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, _yz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextDown(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextDown(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextDown(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.nextDown(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextUp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextUp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextUp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.nextUp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
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

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, rowX * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, rowX * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, rowX * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, rowY * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, rowY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, rowY * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, rowY * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, rowZ * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, rowZ * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, rowZ * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, rowZ * _selfw);
        return outerProduct_api_s29a12022_1(dest, destOffset, rowW, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s29a12022_1(java.lang.foreign.MemorySegment dest, long destOffset, float rowW, float _selfx, float _selfy, float _selfz, float _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 48L, rowW * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 52L, rowW * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 56L, rowW * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 60L, rowW * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rowBase = row.address() + rowOffset;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _rowx = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        float _rowz = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 8L);
        float _roww = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _rowx * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _rowx * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _rowx * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _rowy * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _rowy * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _rowy * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _rowy * _selfw);
        return outerProduct_api_s908a9879_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _rowz, _roww);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s908a9879_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _rowz, float _roww) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _rowz * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 36L, _rowz * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 40L, _rowz * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 44L, _rowz * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 48L, _roww * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 52L, _roww * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 56L, _roww * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 60L, _roww * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponentX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponentY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponentZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _exponentBase = exponent.address() + exponentOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _exponenty = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 4L);
        float _exponentz = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 8L);
        float _exponentw = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, _exponenty));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, _exponentz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _t9 = Math.fma(ontoW, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(ontoZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(ontoX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), ontoY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ontoW * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        float _ontox = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _ontow = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 12L);
        float _t9 = Math.fma(_ontow, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), Math.fma(_ontoz, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(_ontox, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _ontow * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-normalZ, _t3, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-_normalz, _t3, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toRadians(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toRadians(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toRadians(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.toRadians(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = 2.0f * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t4, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t4, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-normalZ, _t4, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = 2.0f * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t4, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t4, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-_normalz, _t4, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        return refract_api_s3d8b998e_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, Math.fma(eta, _t4, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t8))));
    }

    /** Piece 2 of {@code refract_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_s3d8b998e_1(java.lang.foreign.MemorySegment dest, long destOffset, float eta, float _selfx, float _selfy, float _selfz, float _selfw, float _normalx, float _normaly, float _normalz, float _normalw, float _t8, float _t11) {
        if (_t8 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.rint(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.rint(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.rint(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.rint(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.signum(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.signum(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.signum(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.signum(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sin(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.sin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sinh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.sinh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t15 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0) * _t0_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0W) / (edge1W - edge0W)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edge0Base = edge0.address() + edge0Offset;
        long _edge1Base = edge1.address() + edge1Offset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        float _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 12L);
        return smoothstep_api_s2a563cfe_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_s2a563cfe_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, float _edge0x, float _edge0y, float _edge0z, float _edge0w) {
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - _edge0w) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 12L) - _edge0w)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edgeBase = edge.address() + edgeOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _edgey = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 4L);
        float _edgez = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 8L);
        float _edgew = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset) ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tan(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.tan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tanh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tanh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tanh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.tanh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ulp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ulp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ulp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.ulp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat30 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat31 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat32 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        float _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMul_api_se3976806_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_se3976806_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat10, float _mat20, float _mat30, float _mat11, float _mat21, float _mat31, float _mat12, float _mat22, float _mat32, float _mat13, float _mat23) {
        float _mat33 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 60L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 48L), _selfw, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        return rotate_api_sd3dbd06a_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_sd3dbd06a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _quatx, float _quaty, float _quatw, float _t9, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = Math.sin(angle);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        return rotateAxis_api_scbc86a38_1(dest, destOffset, _t0, _selfx, _selfy, _selfz, _selfw, _axisx, _axisy, _axisz, _t1, _t3, _t5);
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_scbc86a38_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _selfx, float _selfy, float _selfz, float _selfw, float _axisx, float _axisy, float _axisz, float _t1, float _t3, float _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        return rotateInverse_api_seadbd8d2_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotateInverse_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_seadbd8d2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _quatx, float _quaty, float _quatw, float _t9, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t0, _selfz * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
