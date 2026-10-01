// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double4Ops} and its sibling kernel units. Not public API.
 */
public final class Double4OpsKernelsSegment {
    private Double4OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, otherX + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ + _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, otherW + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz + _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _otherw + _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) / otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / otherZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) / other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / _otherz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw / _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), b, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, b, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, b, cZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _cy = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), b, c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, b, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, b, _cz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), bX, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, bY, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, bZ, cZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _by, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _bz, _cz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, scalar * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, scalar * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, scalar * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, otherX * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, otherW * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _otherw * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, -_selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - otherZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw - otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - _otherz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw - _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double u, double v, double w) {
        long _destBase = dest.address() + destOffset;
        Double4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, double u, double v, double w) {
        double _t0 = java.lang.Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.cosFromSin(_t4, _t1) * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t4 * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cosFromSin(_t6, _t3) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _t6 * _t0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = dest.address() + destOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY, double vZ, double vW) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, vZ);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, vW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _vw = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _vz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _vw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        long _destBase = dest.address() + destOffset;
        Double4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Double4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p1X, _t7, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        return bezier_api_saac17df3_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_saac17df3_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset), _t7, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8) + Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset), _t6, p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset) * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset), _t0, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return bezier2_api_s72e78db6_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_s72e78db6_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t0, double _t3, double _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return bezier2Tangent_api_sab4e1e95_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_sab4e1e95_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _t1, double _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        double _t1 = 1.0 - t;
        return bezierTangent_api_sc684a402_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0 * t * t, 6.0 * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_sc684a402_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t1, double _t2, double _t5) {
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_s9daa6f65_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s9daa6f65_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (Math.fma(2.0, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRom_api_s4a035724_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s4a035724_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), _t0, Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_api_s4a035724_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s4a035724_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * (Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), _t0, Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_api_s2914e208_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_s2914e208_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p1w = p1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p1Offset + 24L);
        double _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p2w = p2.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p2Offset + 24L);
        double _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _p3w = p3.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, p3Offset + 24L);
        return catmullRomTangent_api_sa7208727_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_sa7208727_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w) {
        double _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_api_sa7208727_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_sa7208727_2(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), Math.fma(3.0 * Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        double _t0 = t * t;
        return hermite_api_sdd758ba8_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * Math.fma(t, t, -t), Math.fma(t - 2.0, _t0, t));
    }

    /** Piece 2 of {@code hermite_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_sdd758ba8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t2, double _t5, double _t7) {
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset) * _t7) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset), _t5, v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset) * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _t0w = t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset + 24L);
        double _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _v1w = v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset + 24L);
        double _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t1w = t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset + 24L);
        return hermiteTangent_api_sb843a137_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, t * t, 6.0 * Math.fma(t, t, -t), 6.0 * Math.fma(-t, t, t));
    }

    /** Piece 2 of {@code hermiteTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_sb843a137_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t0, double _t6, double _t7) {
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t0Offset) * _t9) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, t1Offset), _t8, v1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, v1Offset) * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(t, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(t, otherZ - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(t, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(t, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(t, _otherz - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(tY, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _tBase = t.address() + tOffset;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _ty = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _tz = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 16L);
        double _tw = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_api_s542c9793_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s542c9793_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
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
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_s542c9793_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57)), _t28 * Math.cos(_t61));
    }

    /** Piece 3 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s542c9793_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _sp0, double _t66) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Double4OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
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
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
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
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(t, otherY - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_s8f928b54_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0 / java.lang.Math.sqrt(_t9)), (1.0 / java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s8f928b54_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t9, double _t10, double _t11, double _t14) {
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
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
        return slerp_api_s8f928b54_2(dest, destOffset, src, srcOffset, other, otherOffset, t, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s8f928b54_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31, double _t49, double _t50, double _t51, double _t52, double _t57) {
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double4OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t7 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
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
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
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
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(t, _othery - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.abs(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.acos(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.acos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.acos(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.acos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(scalar, bX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(scalar, bY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(scalar, bZ, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(scalar, b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(scalar, _by, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(scalar, _bz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(bX, cX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(bY, cY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(bZ, cZ, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _by = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _bw = b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset + 24L);
        double _cy = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        double _cw = c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(b.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, cOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_by, _cy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_bz, _cz, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        double _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        double _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        double _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Double4OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        return angleBetween_degenerate_api_see039fb1_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_see039fb1_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41) {
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        double _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        double _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        double _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        return angleBetween_api_s789d8bf3_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code angleBetween_api}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_api_s789d8bf3_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double4OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t6 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        return angleBetween_degenerate_api_sf89d9cc8_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_sf89d9cc8_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t36, double _t37, double _t38, double _t39) {
        double _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        double _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.asin(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.asin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.asin(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.asin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ, double xW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ, double xW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), xX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, xY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, xZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _xBase = x.address() + xOffset;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _xy = x.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, xOffset + 8L);
        double _xz = x.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, xOffset + 16L);
        double _xw = x.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, xOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), x.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, xOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, _xy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, _xz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.cbrt(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cbrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cbrt(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cbrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.ceil(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ceil(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), minX), maxX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _minBase = min.address() + minOffset;
        long _maxBase = max.address() + maxOffset;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _miny = min.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, minOffset + 8L);
        double _minz = min.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, minOffset + 16L);
        double _minw = min.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, minOffset + 24L);
        double _maxy = max.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, maxOffset + 8L);
        double _maxz = max.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, maxOffset + 16L);
        double _maxw = max.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, maxOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), min.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, minOffset)), max.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, maxOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)));
    }

    public static double compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static double compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static double compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ, double signW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ, double signW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), signX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, signY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, signZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _signBase = sign.address() + signOffset;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _signy = sign.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, signOffset + 8L);
        double _signz = sign.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, signOffset + 16L);
        double _signw = sign.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, signOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), sign.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, signOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, _signy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, _signz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.cos(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cos(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.cosh(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cosh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cosh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.cosh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        long _wBase = w.address() + wOffset;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        double _vw = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 24L);
        double _wx = w.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, wOffset);
        double _wy = w.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, wOffset + 8L);
        double _wz = w.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, wOffset + 16L);
        double _ww = w.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, wOffset + 24L);
        return cross_api_s785e5798_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, Math.fma(_vy, _wz, -(_vz * _wy)), Math.fma(_vz, _ww, -(_vw * _wz)), Math.fma(_vy, _ww, -(_vw * _wy)), Math.fma(_vx, _wz, -(_vz * _wx)), Math.fma(_vx, _ww, -(_vw * _wx)), Math.fma(_vx, _wy, -(_vy * _wx)));
    }

    /** Piece 2 of {@code cross_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_s785e5798_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.toDegrees(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toDegrees(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.toDegrees(_selfw));
        return dest;
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), Math.fma(otherZ, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), Math.fma(otherX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), otherY * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))));
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L) * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))));
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.exp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.exp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.exp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(2.0, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(2.0, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(2.0, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(2.0, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.expm1(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.expm1(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.expm1(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.expm1(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, IOffset + 24L), Nref.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, NrefOffset + 24L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, IOffset + 16L), Nref.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, NrefOffset + 16L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, IOffset), Nref.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, NrefOffset), I.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, IOffset + 8L) * Nref.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, NrefOffset + 8L)))) < 0.0 ? 1.0 : -1.0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.floor(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.floor(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.floor(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.floor(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, yZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _yy = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        double _yw = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, _yz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 1.0 / src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 1.0 / _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 1.0 / _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0 / _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, (1.0 / java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, (1.0 / java.lang.Math.sqrt(_selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, (1.0 / java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static double length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        return java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static double lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.log(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.log10(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log10(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log10(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log10(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.log1p(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log1p(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log1p(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log1p(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.log2(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log2(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log2(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.log2(_selfw));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - otherW);
    }

    public static double manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L));
    }

    public static double manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.max(_selfz, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.max(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.max(_selfz, otherZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.max(_selfw, otherW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.max(_selfz, _otherz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(_selfz, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(_selfz, otherZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(_selfw, otherW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _othery = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _otherw = other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.min(_selfz, _otherz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, yZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _yx = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset);
        double _yy = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        double _yw = y.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, yOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, _yz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextDown(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextDown(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextDown(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.nextDown(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextUp(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextUp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextUp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.nextUp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t4);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t5);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw * _t5);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, rowX * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, rowX * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, rowX * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, rowY * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, rowY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, rowY * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, rowY * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, rowZ * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, rowZ * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 80L, rowZ * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 88L, rowZ * _selfw);
        return outerProduct_api_s8aedf2ae_1(dest, destOffset, rowW, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s8aedf2ae_1(java.lang.foreign.MemorySegment dest, long destOffset, double rowW, double _selfx, double _selfy, double _selfz, double _selfw) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 96L, rowW * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 104L, rowW * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 112L, rowW * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 120L, rowW * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rowBase = row.address() + rowOffset;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _rowx = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset);
        double _rowy = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset + 8L);
        double _rowz = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset + 16L);
        double _roww = row.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rowOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _rowx * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _rowx * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _rowx * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _rowy * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _rowy * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _rowy * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _rowy * _selfw);
        return outerProduct_api_s908a9879_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _rowz, _roww);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s908a9879_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _rowz, double _roww) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _rowz * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 72L, _rowz * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 80L, _rowz * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 88L, _rowz * _selfw);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 96L, _roww * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 104L, _roww * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 112L, _roww * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 120L, _roww * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), exponentX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponentY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponentZ));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _exponentBase = exponent.address() + exponentOffset;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _exponenty = exponent.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, exponentOffset + 8L);
        double _exponentz = exponent.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, exponentOffset + 16L);
        double _exponentw = exponent.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, exponentOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), exponent.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, exponentOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, _exponenty));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, _exponentz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        double _t9 = Math.fma(ontoW, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), Math.fma(ontoZ, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), Math.fma(ontoX, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), ontoY * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ontoY * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ontoZ * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, ontoW * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        double _ontox = onto.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, ontoOffset);
        double _ontoy = onto.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, ontoOffset + 8L);
        double _ontoz = onto.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, ontoOffset + 16L);
        double _ontow = onto.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, ontoOffset + 24L);
        double _t9 = Math.fma(_ontow, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), Math.fma(_ontoz, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), Math.fma(_ontox, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _ontoy * src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _ontoy * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _ontoz * _t9);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _ontow * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-normalY, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-normalZ, _t3, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_normaly, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-_normalz, _t3, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.toRadians(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toRadians(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toRadians(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.toRadians(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = 2.0 * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-normalX, _t4, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-normalY, _t4, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-normalZ, _t4, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = 2.0 * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-_normalx, _t4, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-_normaly, _t4, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-_normalz, _t4, _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normalw = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 24L);
        double _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        return refract_api_sfce3c69_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8))));
    }

    /** Piece 2 of {@code refract_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_sfce3c69_1(java.lang.foreign.MemorySegment dest, long destOffset, double eta, double _selfx, double _selfy, double _selfz, double _selfw, double _normalx, double _normaly, double _normalz, double _normalw, double _t8, double _t11) {
        if (_t8 >= 0.0) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.rint(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.rint(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.rint(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.rint(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.signum(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.signum(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.signum(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.signum(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.sin(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sin(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.sin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.sinh(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sinh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sinh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.sinh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        double _t15 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0) * _t0_inv));
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0) * _t0_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - edge0W) / (edge1W - edge0W)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edge0Base = edge0.address() + edge0Offset;
        long _edge1Base = edge1.address() + edge1Offset;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        double _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset);
        double _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 8L);
        double _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 16L);
        double _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge0Offset + 24L);
        return smoothstep_api_s2a563cfe_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_s2a563cfe_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge1Offset) - _edge0x)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _edge0y) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge1Offset + 8L) - _edge0y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _edge0z) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge1Offset + 16L) - _edge0z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) - _edge0w) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edge1Offset + 24L) - _edge0w)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.sqrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.sqrt(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) < edge ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edge ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edge ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) < edgeX ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edgeY ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edgeZ ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < edgeW ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edgeBase = edge.address() + edgeOffset;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _edgey = edge.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edgeOffset + 8L);
        double _edgez = edge.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edgeOffset + 16L);
        double _edgew = edge.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edgeOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) < edge.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, edgeOffset) ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < _edgey ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < _edgez ? 0.0 : 1.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw < _edgew ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.tan(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tan(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.tan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.tanh(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tanh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tanh(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.tanh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw >= 0.0 ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.ulp(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ulp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ulp(_selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.ulp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat30 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 24L);
        double _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat31 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat32 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 88L);
        double _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        return preMul_api_se3976806_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_se3976806_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _mat10, double _mat20, double _mat30, double _mat11, double _mat21, double _mat31, double _mat12, double _mat22, double _mat32, double _mat13, double _mat23) {
        double _mat33 = mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 120L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 96L), _selfw, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 64L), _selfz, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, matOffset + 32L) * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        return rotate_api_sd3dbd06a_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_sd3dbd06a_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _quatx, double _quaty, double _quatw, double _t9, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        return rotateAxis_api_sed18b749_1(dest, destOffset, _t0, _selfx, _selfy, _selfz, _selfw, _axisx, _axisy, _axisz, _t1, _t3, _t5);
    }

    /** Piece 2 of {@code rotateAxis_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_sed18b749_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _selfx, double _selfy, double _selfz, double _selfw, double _axisx, double _axisy, double _axisz, double _t1, double _t3, double _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        return rotateInverse_api_seadbd8d2_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotateInverse_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_seadbd8d2_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfz, double _selfw, double _quatx, double _quaty, double _quatw, double _t9, double _t10, double _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfy, _t0, _selfz * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _selfw);
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
