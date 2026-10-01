// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double3OpsKernelsSegment {
    private Double3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, otherX + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY + _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ + _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery + _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz + _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / scalar);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / scalar);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / otherX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / otherY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) / other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy / _othery);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz / _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double b, double cX, double cY, double cZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b, cX));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, b, cY));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, b, cZ));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b) + (cX)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (b) + (cY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (b) + (cZ)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b, c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, b, _cy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, b, _cz));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, double b) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b) + (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (b) + (_cy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (b) + (_cz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), bX, cX));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, bY, cY));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, bZ, cZ));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (bX) + (cX)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (bY) + (cY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (bZ) + (cZ)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _by, _cy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) + (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_by) + (_cy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_bz) + (_cz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, scalar * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, scalar * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, scalar * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, otherX * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, otherY * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, otherZ * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _othery * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _otherz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, -src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - otherY);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy - _othery);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz - _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double u, double v) {
        long _destBase = dest.address() + destOffset;
        Double3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, double u, double v) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t4 * Math.cosWithSin(_t1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t4 * Math.sinWithCos(_t1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(2.0, u, -1.0));
            return dest;
        } else {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t4 * Math.cosWithSin(_t1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t4 * Math.sinWithCos(_t1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((2.0) * (u) - (1.0)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY, double vZ) {
        long _destBase = dest.address() + destOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY, double vZ) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _vy = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(_JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, v.get(_JAVA_DOUBLE_UNALIGNED, vOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _vy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _vz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        long _destBase = dest.address() + destOffset;
        Double3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Double3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p1X, _t7, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p1X) * (_t7) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset), _t7, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset), _t6, p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) * _t2));
        return bezier_api_fma_s43e0830_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma_s43e0830_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t2, double _t6, double _t7, double _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset)) * (_t7) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t8)) + ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset)) * (_t6) + (p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) * _t2)));
        return bezier_api_mulAdd_s7b645471_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd_s7b645471_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t2, double _t6, double _t7, double _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset), _t0, java.lang.Math.fma(p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset), _t3, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset)) * (_t0) + (((p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset)) * (_t3) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t2, (p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t2) + ((p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _p1x) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        return bezierTangent_api_fma_s35f9af59_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma_s35f9af59_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t2, double _t5, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        return bezierTangent_api_mulAdd_s840b67c2_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd_s840b67c2_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t2, double _t5, double _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_fma_s185752b4_1(dest, destOffset, p1Z, p2Z, p3Z, t, _selfz, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s185752b4_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p2Z, double p3Z, double t, double _selfz, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t0 = t * t;
        return catmullRom_api_fma_s302f430b_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s302f430b_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _p1x, double _p1y, double _p1z, double _p2x, double _p2y, double _p2z, double _p3x, double _p3y, double _p3z, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t0 = t * t;
        return catmullRom_api_mulAdd_sdc0792b4_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sdc0792b4_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _p1x, double _p1y, double _p1z, double _p2x, double _p2y, double _p2z, double _p3x, double _p3y, double _p3z, double _t0, double _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_api_fma_s7c59aa0d_1(dest, destOffset, p1Z, p2Z, p3Z, t, _selfz, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_s7c59aa0d_1(java.lang.foreign.MemorySegment dest, long destOffset, double p1Z, double p2Z, double p3Z, double t, double _selfz, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        return catmullRomTangent_api_fma_sdb93b2bc_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_sdb93b2bc_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfx, double _selfy, double _selfz, double _p1x, double _p1y, double _p1z, double _p2x, double _p2y, double _p2z, double _p3x, double _p3y, double _p3z, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _p3x = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset);
        double _p3y = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 8L);
        double _p3z = p3.get(_JAVA_DOUBLE_UNALIGNED, p3Offset + 16L);
        double _t0 = t * t;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_api_mulAdd_s9177d4dd_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s9177d4dd_1(java.lang.foreign.MemorySegment dest, long destOffset, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        return hermite_api_fma_s2863995b_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0, _t0, t), java.lang.Math.fma(3.0, _t0, -(_t2 + _t2)), java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0)));
    }

    /** Piece 2 of {@code hermite_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma_s2863995b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t5, double _t7, double _t9, double _t10) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t10, t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t7) + java.lang.Math.fma(t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset), _t5, v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        return hermite_api_mulAdd_s22893c70_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, t * ((t) * (t) - (t)), ((t - 2.0) * (_t0) + (t)), ((3.0) * (_t0) - (_t2 + _t2)), ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0)))));
    }

    /** Piece 2 of {@code hermite_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd_s22893c70_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t5, double _t7, double _t9, double _t10) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t10) + (t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t7)) + ((t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset)) * (_t5) + (v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t0 = t * t;
        return hermiteTangent_api_fma_s19be2160_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, 6.0 * java.lang.Math.fma(t, t, -t), 6.0 * java.lang.Math.fma(-t, t, t), java.lang.Math.fma(3.0, _t0, -(t + t)), java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0)));
    }

    /** Piece 2 of {@code hermiteTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma_s19be2160_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t6, double _t7, double _t8, double _t9) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _t6, t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t9) + java.lang.Math.fma(t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset), _t8, v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0y = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 8L);
        double _t0z = t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset + 16L);
        double _v1y = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 8L);
        double _v1z = v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset + 16L);
        double _t1y = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 8L);
        double _t1z = t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset + 16L);
        double _t0 = t * t;
        return hermiteTangent_api_mulAdd_sdcabaefd_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, 6.0 * ((t) * (t) - (t)), 6.0 * ((t) - (t) * (t)), ((3.0) * (_t0) - (t + t)), ((3.0) * (_t0) + (((1.0) - (4.0) * (t)))));
    }

    /** Piece 2 of {@code hermiteTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd_sdcabaefd_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t6, double _t7, double _t8, double _t9) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) * (_t6) + (t0.get(_JAVA_DOUBLE_UNALIGNED, t0Offset) * _t9)) + ((t1.get(_JAVA_DOUBLE_UNALIGNED, t1Offset)) * (_t8) + (v1.get(_JAVA_DOUBLE_UNALIGNED, v1Offset) * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((tY) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _tBase = t.address() + tOffset;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _ty = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _tz = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t.get(_JAVA_DOUBLE_UNALIGNED, tOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _ty = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        double _tz = t.get(_JAVA_DOUBLE_UNALIGNED, tOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t.get(_JAVA_DOUBLE_UNALIGNED, tOffset)) * (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_fma_s16ae1918_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s16ae1918_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_api_mulAdd_sefca3e73_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sefca3e73_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
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
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        return slerp_api_fma_s3f688c7b_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, (1.0 / java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s3f688c7b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _otherx, double _othery, double _otherz, double _t12, double _t14, double _t17, double _t20, double _t24) {
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        double _t33 = java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17));
        double _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        return slerp_api_mulAdd_se0fa7f4_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, (1.0 / java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_se0fa7f4_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double _otherx, double _othery, double _otherz, double _t12, double _t14, double _t17, double _t20, double _t24) {
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        double _t33 = ((_otherz) * (_t12) - (_t26 * _t17));
        double _t34 = ((_otherx) * (_t12) - (_t26 * _t14));
        double _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
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
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_fma_s50cbcbcb_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s50cbcbcb_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_api_mulAdd_s518a3be0_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s518a3be0_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
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
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        return slerp_api_fma_s5fd9d918_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, (1.0 / java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s5fd9d918_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon, double _otherx, double _othery, double _otherz, double _t12, double _t14, double _t17, double _t20, double _t24) {
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        double _t33 = java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17));
        double _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        return slerp_api_mulAdd_s4619cc57_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, (1.0 / java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s4619cc57_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon, double _otherx, double _othery, double _otherz, double _t12, double _t14, double _t17, double _t20, double _t24) {
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        double _t33 = ((_otherz) * (_t12) - (_t26 * _t17));
        double _t34 = ((_otherx) * (_t12) - (_t26 * _t14));
        double _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
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
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t, double epsilon) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
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
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.acos(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.acos(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.acos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(scalar, bX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, bY, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(scalar, bZ, _selfz));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((scalar) * (bX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((scalar) * (bY) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((scalar) * (bZ) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(scalar, b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, _by, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((scalar) * (b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((scalar) * (_by) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((scalar) * (_bz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(bX, cX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(bY, cY, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(bZ, cZ, _selfz));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((bX) * (cX) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((bY) * (cY) + (_selfy)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((bZ) * (cZ) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(b.get(_JAVA_DOUBLE_UNALIGNED, bOffset), c.get(_JAVA_DOUBLE_UNALIGNED, cOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_by, _cy, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _by = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 8L);
        double _bz = b.get(_JAVA_DOUBLE_UNALIGNED, bOffset + 16L);
        double _cy = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 8L);
        double _cz = c.get(_JAVA_DOUBLE_UNALIGNED, cOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((b.get(_JAVA_DOUBLE_UNALIGNED, bOffset)) * (c.get(_JAVA_DOUBLE_UNALIGNED, cOffset)) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_by) * (_cy) + (_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_bz) * (_cz) + (_selfz)));
        return dest;
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3);
        double _t13 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t14 = java.lang.Math.fma(otherZ, _selfx, _t5) - java.lang.Math.fma(otherX, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Double3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3));
        double _t13 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t14 = ((otherZ) * (_selfx) + (_t5)) - ((otherX) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Double3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static double angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3);
        double _t13 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t14 = java.lang.Math.fma(_otherz, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Double3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3));
        double _t13 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t14 = ((_otherz) * (_selfx) + (_t5)) - ((_otherx) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Double3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
    }

    public static double angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        return angleBetween_degenerate_api_fma_s753ecfb1_1(_t8, _t9, _t10, _t11, _t12, _t13, _t29, _t26 * _t29, _t27 * _t29, _t28 * _t29);
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_fma_s753ecfb1_1(double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t29, double _t33, double _t34, double _t35) {
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        return angleBetween_degenerate_api_mulAdd_s987d4298_1(_t8, _t9, _t10, _t11, _t12, _t13, _t28, _t29, _t26 * _t29, _t27 * _t29);
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_api_mulAdd_s987d4298_1(double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t28, double _t29, double _t33, double _t34) {
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.asin(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.asin(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.asin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double x) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), x));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, x));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double xX, double xY, double xZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), xX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, xY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _xBase = x.address() + xOffset;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _xy = x.get(_JAVA_DOUBLE_UNALIGNED, xOffset + 8L);
        double _xz = x.get(_JAVA_DOUBLE_UNALIGNED, xOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), x.get(_JAVA_DOUBLE_UNALIGNED, xOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.atan2(_selfy, _xy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cbrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cbrt(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cbrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.ceil(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ceil(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double min, double max) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), min), max));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, min), max));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), minX), maxX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, minY), maxY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _minBase = min.address() + minOffset;
        long _maxBase = max.address() + maxOffset;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _miny = min.get(_JAVA_DOUBLE_UNALIGNED, minOffset + 8L);
        double _minz = min.get(_JAVA_DOUBLE_UNALIGNED, minOffset + 16L);
        double _maxy = max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset + 8L);
        double _maxz = max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), min.get(_JAVA_DOUBLE_UNALIGNED, minOffset)), max.get(_JAVA_DOUBLE_UNALIGNED, maxOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_api_mulAdd(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - lineStartZ, java.lang.Math.fma(_t1, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - lineStartX, _t2 * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t1, _t14, lineStartX));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, lineStartY));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, lineStartX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, lineStartY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, lineStartZ);
        }
        return dest;
    }

    /** {@code closestPointOnLine_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - lineStartZ) + (((_t1) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - lineStartX) + (_t2 * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - lineStartY))))) / _t10));
        if (_t10 > 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t1) * (_t14) + (lineStartX)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (lineStartY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t0) * (_t14) + (lineStartZ)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, lineStartX);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, lineStartY);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, lineStartZ);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _lineStartBase = lineStart.address() + lineStartOffset;
        long _lineEndBase = lineEnd.address() + lineEndOffset;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_api_fma(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return closestPointOnLine_api_mulAdd(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@code closestPointOnLine_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _lineStartx = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset);
        double _lineStarty = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset + 8L);
        double _lineStartz = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset + 16L);
        double _t0 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset + 16L) - _lineStartz;
        double _t1 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset) - _lineStartx;
        double _t2 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset + 8L) - _lineStarty;
        return closestPointOnLine_api_fma_s75c412e7_1(dest, destOffset, src, srcOffset, _lineStartx, _lineStarty, _lineStartz, _t0, _t1, _t2, java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** Piece 2 of {@code closestPointOnLine_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_fma_s75c412e7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _lineStartx, double _lineStarty, double _lineStartz, double _t0, double _t1, double _t2, double _t10) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _lineStartz, java.lang.Math.fma(_t1, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _lineStartx, _t2 * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _lineStarty))) / _t10));
        if (_t10 > 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t1, _t14, _lineStartx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t14, _lineStarty));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _lineStartx);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _lineStarty);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _lineStartz);
        }
        return dest;
    }

    /** {@code closestPointOnLine_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _lineStartx = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset);
        double _lineStarty = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset + 8L);
        double _lineStartz = lineStart.get(_JAVA_DOUBLE_UNALIGNED, lineStartOffset + 16L);
        double _t0 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset + 16L) - _lineStartz;
        double _t1 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset) - _lineStartx;
        double _t2 = lineEnd.get(_JAVA_DOUBLE_UNALIGNED, lineEndOffset + 8L) - _lineStarty;
        return closestPointOnLine_api_mulAdd_s7785852_1(dest, destOffset, src, srcOffset, _lineStartx, _lineStarty, _lineStartz, _t0, _t1, _t2, ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
    }

    /** Piece 2 of {@code closestPointOnLine_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd_s7785852_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _lineStartx, double _lineStarty, double _lineStartz, double _t0, double _t1, double _t2, double _t10) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _lineStartz) + (((_t1) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _lineStartx) + (_t2 * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _lineStarty))))) / _t10));
        if (_t10 > 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t1) * (_t14) + (_lineStartx)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t14) + (_lineStarty)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t0) * (_t14) + (_lineStartz)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _lineStartx);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _lineStarty);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _lineStartz);
        }
        return dest;
    }

    public static double compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) + src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L));
    }

    public static double compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return Math.max(Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L));
    }

    public static double compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return Math.min(Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L));
    }

    public static double compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sign) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), sign));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, sign));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double signX, double signY, double signZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), signX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, signY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _signBase = sign.address() + signOffset;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _signy = sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset + 8L);
        double _signz = sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), sign.get(_JAVA_DOUBLE_UNALIGNED, signOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.copySign(_selfy, _signy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cos(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cos(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.cosh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.cosh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.cosh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = -(otherY * _selfz);
        double _t4 = -(otherZ * _selfx);
        double _t5 = -(otherX * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(otherX, _selfz, _t4) - java.lang.Math.fma(otherZ, _selfx, _t4));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(otherY, _selfx, _t5) - java.lang.Math.fma(otherX, _selfy, _t5));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = -(otherY * _selfz);
        double _t4 = -(otherZ * _selfx);
        double _t5 = -(otherX * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((otherX) * (_selfz) + (_t4)) - ((otherZ) * (_selfx) + (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((otherY) * (_selfx) + (_t5)) - ((otherX) * (_selfy) + (_t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_otherx, _selfz, _t4) - java.lang.Math.fma(_otherz, _selfx, _t4));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_othery, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfy, _t5));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_otherx) * (_selfz) + (_t4)) - ((_otherz) * (_selfx) + (_t4)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_othery) * (_selfx) + (_t5)) - ((_otherx) * (_selfy) + (_t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.toDegrees(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toDegrees(_selfz));
        return dest;
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ;
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX;
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
            double _t1 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherZ, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(otherX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)));
        } else {
            return ((otherZ) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((otherX) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (otherY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))));
        }
    }

    public static double dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset), src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)));
        } else {
            return ((other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L) * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)))));
        }
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.exp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.exp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.exp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(2.0, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(2.0, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(2.0, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.expm1(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.expm1(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.expm1(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = java.lang.Math.fma(I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 16L), Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 16L), java.lang.Math.fma(I.get(_JAVA_DOUBLE_UNALIGNED, IOffset), Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset), I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 8L) * Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 8L))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t3);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = ((I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 16L)) * (Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 16L)) + (((I.get(_JAVA_DOUBLE_UNALIGNED, IOffset)) * (Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset)) + (I.get(_JAVA_DOUBLE_UNALIGNED, IOffset + 8L) * Nref.get(_JAVA_DOUBLE_UNALIGNED, NrefOffset + 8L))))) < 0.0 ? 1.0 : -1.0;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) * _t3);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.floor(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.floor(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.floor(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), yX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, yY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _yy = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), y.get(_JAVA_DOUBLE_UNALIGNED, yOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.hypot(_selfy, _yy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 1.0 / src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 1.0 / _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 1.0 / _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, (1.0 / java.lang.Math.sqrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, (1.0 / java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static double length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            return java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        }
    }

    public static double lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            return java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            return ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        }
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log10(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log10(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log10(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log1p(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log1p(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log1p(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.log2(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.log2(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.log2(_selfz));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) - otherX) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - otherY) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - otherZ);
    }

    public static double manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L));
    }

    public static double manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, otherY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.max(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.max(_selfy, _othery));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, scalar));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, otherY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.min(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.min(_selfy, _othery));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double y) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, y));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double yX, double yY, double yZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, yY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _yx = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset);
        double _yy = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 8L);
        double _yz = y.get(_JAVA_DOUBLE_UNALIGNED, yOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, flooredMod(_selfy, _yy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextDown(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextDown(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextDown(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.nextUp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.nextUp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.nextUp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalize_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t3);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t3);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_api_mulAdd(dest, destOffset, src, srcOffset, length);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code normalizeMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizeMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double length) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy * _t4);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz * _t4);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t17 = java.lang.Math.fma(otherX, _selfz, _t5) - java.lang.Math.fma(otherZ, _selfx, _t5);
        double _t18 = java.lang.Math.fma(otherZ, _selfy, _t6) - java.lang.Math.fma(otherY, _selfz, _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Double3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t17 = ((otherX) * (_selfz) + (_t5)) - ((otherZ) * (_selfx) + (_t5));
        double _t18 = ((otherZ) * (_selfy) + (_t6)) - ((otherY) * (_selfz) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Double3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_api_fma_sf4cfbd0b_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_api_fma_sf4cfbd0b_1(double normalX, double normalY, double normalZ, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t33, double _t39, double _t40, double _t41) {
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_api_mulAdd_s527cf78a_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_api_mulAdd_s527cf78a_1(double normalX, double normalY, double normalZ, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t33, double _t39, double _t40, double _t41) {
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static double orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t16 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t17 = java.lang.Math.fma(_otherx, _selfz, _t5) - java.lang.Math.fma(_otherz, _selfx, _t5);
        double _t18 = java.lang.Math.fma(_otherz, _selfy, _t6) - java.lang.Math.fma(_othery, _selfz, _t6);
        return orientedAngle_api_fma_s4f0a2cc7_1(src, srcOffset, other, otherOffset, normal, normalOffset, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18)));
    }

    /** Piece 2 of {@code orientedAngle_api_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_api_fma_s4f0a2cc7_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _normalx, double _normaly, double _normalz, double _t7, double _t16, double _t17, double _t18, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
        return java.lang.Math.fma(_t16, _normalz * _t7, java.lang.Math.fma(_t17, _normaly * _t7, _t18 * (_normalx * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t16 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t17 = ((_otherx) * (_selfz) + (_t5)) - ((_otherz) * (_selfx) + (_t5));
        double _t18 = ((_otherz) * (_selfy) + (_t6)) - ((_othery) * (_selfz) + (_t6));
        return orientedAngle_api_mulAdd_s3cac592_1(src, srcOffset, other, otherOffset, normal, normalOffset, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18)))));
    }

    /** Piece 2 of {@code orientedAngle_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_api_mulAdd_s3cac592_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _normalx, double _normaly, double _normalz, double _t7, double _t16, double _t17, double _t18, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
        return ((_t16) * (_normalz * _t7) + (((_t17) * (_normaly * _t7) + (_t18 * (_normalx * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative() && normal.isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static double orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        return orientedAngle_degenerate_api_fma_sa5b73ac6_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, -(_t13 * _t10), java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19), java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_api_fma_sa5b73ac6_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t21, double _t30, double _t31) {
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _otherx = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset);
        double _othery = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 8L);
        double _otherz = other.get(_JAVA_DOUBLE_UNALIGNED, otherOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        return orientedAngle_degenerate_api_mulAdd_s970978d_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, -(_t13 * _t10), ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19)), ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20)));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_api_mulAdd_s970978d_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t21, double _t30, double _t31) {
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rowX, double rowY, double rowZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rowX, double rowY, double rowZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, rowX * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, rowX * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, rowY * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, rowY * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, rowY * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, rowZ * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, rowZ * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, rowZ * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rowBase = row.address() + rowOffset;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _rowx = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset);
        double _rowy = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 8L);
        double _rowz = row.get(_JAVA_DOUBLE_UNALIGNED, rowOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _rowx * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _rowx * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, _rowy * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _rowy * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _rowy * _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _rowz * _selfx);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _rowz * _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _rowz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfy);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, -_selfx);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfz);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, -_selfy);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponent) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponent));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponent));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double exponentX, double exponentY, double exponentZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponentX));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, exponentY));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _exponentBase = exponent.address() + exponentOffset;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _exponenty = exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset + 8L);
        double _exponentz = exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), exponent.get(_JAVA_DOUBLE_UNALIGNED, exponentOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.pow(_selfy, _exponenty));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double ontoX, double ontoY, double ontoZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t7 = java.lang.Math.fma(ontoZ, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(ontoX, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), ontoY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ontoX * _t7);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ontoY * _t7);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ontoZ * _t7);
            return dest;
        } else {
            double _t7 = ((ontoZ) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((ontoX) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (ontoY * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ontoX * _t7);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ontoY * _t7);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ontoZ * _t7);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _ontox = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset);
        double _ontoy = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 8L);
        double _ontoz = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 16L);
        double _t7 = java.lang.Math.fma(_ontoz, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), java.lang.Math.fma(_ontox, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset), _ontoy * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))) / java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _ontox * _t7);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _ontoy * _t7);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _ontoz * _t7);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _ontox = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset);
        double _ontoy = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 8L);
        double _ontoz = onto.get(_JAVA_DOUBLE_UNALIGNED, ontoOffset + 16L);
        double _t7 = ((_ontoz) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L)) + (((_ontox) * (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)) + (_ontoy * src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))))) / ((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _ontox * _t7);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _ontoy * _t7);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _ontoz * _t7);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t2 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t2, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalY, _t2, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-normalZ, _t2, _selfz));
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t2 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t2)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (normalY) * (_t2)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (normalZ) * (_t2)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t2 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t2, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normaly, _t2, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t2 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (_normaly) * (_t2)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (_normalz) * (_t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.toRadians(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.toRadians(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.toRadians(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalY, _t3, _selfy));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-normalZ, _t3, _selfz));
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t3 = 2.0 * ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t3)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (normalY) * (_t3)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (normalZ) * (_t3)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t3 = 2.0 * java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t3 = 2.0 * ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) - (_normaly) * (_t3)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) - (_normalz) * (_t3)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t3 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((eta) * (_selfx) - (normalX * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((eta) * (_selfy) - (normalY * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((eta) * (_selfz) - (normalZ * _t10)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t3 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, double eta) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _normalx = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normaly = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _normalz = normal.get(_JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _t3 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((eta) * (_selfx) - (_normalx * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((eta) * (_selfy) - (_normaly * _t10)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((eta) * (_selfz) - (_normalz * _t10)));
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.rint(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.rint(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.rint(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.signum(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.signum(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.signum(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.sin(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sin(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.sinh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.sinh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.sinh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t10 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0) * _t0_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t10 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0) * _t0_inv));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t10)) * _t10 * _t10);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t11)) * _t11 * _t11);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0Z) / (edge1Z - edge0Z)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - edge0Z) / (edge1Z - edge0Z)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edge0Base = edge0.address() + edge0Offset;
        long _edge1Base = edge1.address() + edge1Offset;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _edge0x = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset);
        double _edge0y = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset + 8L);
        double _edge0z = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset + 16L);
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _edge0y) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 8L) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _edge0z) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 16L) - _edge0z)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _edge0x = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset);
        double _edge0y = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset + 8L);
        double _edge0z = edge0.get(_JAVA_DOUBLE_UNALIGNED, edge0Offset + 16L);
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _edge0y) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 8L) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _edge0z) / (edge1.get(_JAVA_DOUBLE_UNALIGNED, edge1Offset + 16L) - _edge0z)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.sqrt(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.sqrt(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edge) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edge ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edge ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double edgeX, double edgeY, double edgeZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edgeX ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < edgeY ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < edgeZ ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edgeBase = edge.address() + edgeOffset;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _edgey = edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset + 8L);
        double _edgez = edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) < edge.get(_JAVA_DOUBLE_UNALIGNED, edgeOffset) ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy < _edgey ? 0.0 : 1.0);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz < _edgez ? 0.0 : 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.tan(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tan(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.tanh(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.tanh(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.tanh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t20 * _t25);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t20 * _t25);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t19 * _t25);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
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
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t57 = _t40 * _t44;
        return triangleNormal_degenerate_api_fma_s55870070_1(dest, destOffset, _t38 * _t44, _t53, _t54, _t55, _t43 * _t45, _t57, -(_t54 * _t55), -(_t57 * _t53));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s55870070_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t61, double _t62) {
        double _t63 = -(_t52 * _t56);
        double _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t82 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t83 * _t79);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t83 * _t77);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
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
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t57 = _t40 * _t44;
        return triangleNormal_degenerate_api_mulAdd_sc2409cb1_1(dest, destOffset, _t38 * _t44, _t53, _t54, _t55, _t43 * _t45, _t57, -(_t54 * _t55), -(_t57 * _t53));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd_sc2409cb1_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t61, double _t62) {
        double _t63 = -(_t52 * _t56);
        double _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t82 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t83 * _t79);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t83 * _t77);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L) - _selfy;
        double _t1 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L) - _selfz;
        double _t2 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L) - _selfz;
        double _t3 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L) - _selfy;
        double _t4 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset) - _selfx;
        double _t5 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        return triangleNormal_api_fma_sc735fdb0_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, _t18, _t19, _t20, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20)));
    }

    /** Piece 2 of {@code triangleNormal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma_sc735fdb0_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double _t18, double _t19, double _t20, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t20 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L) - _selfy;
        double _t1 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L) - _selfz;
        double _t2 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L) - _selfz;
        double _t3 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L) - _selfy;
        double _t4 = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset) - _selfx;
        double _t5 = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        return triangleNormal_api_mulAdd_s6b6c8b0b_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, _t18, _t19, _t20, ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20)))));
    }

    /** Piece 2 of {@code triangleNormal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd_s6b6c8b0b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, double _t18, double _t19, double _t20, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t20 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t19 * _t25);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative()) return Double3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        return triangleNormal_degenerate_api_fma_s45e8ab9b_1(dest, destOffset, _p1x * _t19 - _t30, _p1y * _t19 - _t32, _p1z * _t19 - _t34, _p2y * _t19 - _t32, _p2x * _t19 - _t30, _p2z * _t19 - _t34);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s45e8ab9b_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43) {
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        return triangleNormal_degenerate_api_fma_s45e8ab9b_2(dest, destOffset, _t77, _t78, _t79, _t82, (1.0 / java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s45e8ab9b_2(java.lang.foreign.MemorySegment dest, long destOffset, double _t77, double _t78, double _t79, double _t82, double _t83) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t82 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t83 * _t79);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t83 * _t77);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _p1x = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset);
        double _p1y = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 8L);
        double _p1z = p1.get(_JAVA_DOUBLE_UNALIGNED, p1Offset + 16L);
        double _p2x = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset);
        double _p2y = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 8L);
        double _p2z = p2.get(_JAVA_DOUBLE_UNALIGNED, p2Offset + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        return triangleNormal_degenerate_api_mulAdd_sf0404f46_1(dest, destOffset, _p1x * _t19 - _t30, _p1y * _t19 - _t32, _p1z * _t19 - _t34, _p2y * _t19 - _t32, _p2x * _t19 - _t30, _p2z * _t19 - _t34);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd_sf0404f46_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43) {
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (_t82 != 0.0) {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _t83 * _t79);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _t83 * _t77);
        } else {
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, 0.0);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, 0.0);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, Math.ulp(src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, Math.ulp(_selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, Math.ulp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 24L, 1.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L), _selfz, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L) * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L)) * (_selfz) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L) * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat3x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulDirectionMat3x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulDirectionMat3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L), _selfz, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L) * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L)) * (_selfz) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L) * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat4x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulDirectionMat4x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulDirectionMat4x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L), _selfz, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L) * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat4x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L)) * (_selfz) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L) * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulPositionMat3x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulPositionMat3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L), _selfy, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L), _selfz, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L)))));
        return preMulPositionMat3x4_api_fma_s5fb2a05d_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat11, _mat12, _mat13, _mat20, _mat21, _mat22, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat3x4_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_fma_s5fb2a05d_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat11, double _mat12, double _mat13, double _mat20, double _mat21, double _mat22, double _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L)) * (_selfy) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L)) * (_selfz) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L))))))));
        return preMulPositionMat3x4_api_mulAdd_sbdf969a4_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat11, _mat12, _mat13, _mat20, _mat21, _mat22, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat3x4_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_mulAdd_sbdf969a4_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat11, double _mat12, double _mat13, double _mat20, double _mat21, double _mat22, double _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulPositionMat4x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulPositionMat4x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L), _selfy, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L), _selfz, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L)))));
        return preMulPositionMat4x4_api_fma_s105fb8f8_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat4x4_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_fma_s105fb8f8_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat20, double _mat11, double _mat21, double _mat12, double _mat22, double _mat13, double _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat4x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L)) * (_selfy) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L)) * (_selfz) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L))))))));
        return preMulPositionMat4x4_api_mulAdd_sb9b90de3_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat4x4_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_mulAdd_sb9b90de3_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat20, double _mat11, double _mat21, double _mat12, double _mat22, double _mat13, double _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulProject_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Double3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulProject_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulProject_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulProject_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulProject_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        return preMulProject_api_fma_seb2505e0_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_fma_seb2505e0_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat20, double _mat11, double _mat21, double _mat12, double _mat22, double _mat13, double _mat23) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t2_inv = 1.0 / java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L), _selfx, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L), _selfy, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L), _selfz, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 120L))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L), _selfy, java.lang.Math.fma(mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L), _selfz, mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L)))) * _t2_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    /** {@code preMulProject_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _mat10 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 8L);
        double _mat20 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 16L);
        double _mat11 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 40L);
        double _mat21 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 48L);
        double _mat12 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 72L);
        double _mat22 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 80L);
        double _mat13 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 104L);
        double _mat23 = mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 112L);
        return preMulProject_api_mulAdd_s8c5f32b_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_mulAdd_s8c5f32b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, double _selfx, double _selfy, double _selfz, double _mat10, double _mat20, double _mat11, double _mat21, double _mat12, double _mat22, double _mat13, double _mat23) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t2_inv = 1.0 / ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 24L)) * (_selfx) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 56L)) * (_selfy) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 88L)) * (_selfz) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 120L)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 32L)) * (_selfy) + (((mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 64L)) * (_selfz) + (mat.get(_JAVA_DOUBLE_UNALIGNED, matOffset + 96L))))))) * _t2_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))) * _t2_inv);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))) * _t2_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t9 = 2.0 * ((quatX) * (_selfy) - (quatY * _selfx));
        double _t10 = 2.0 * ((quatZ) * (_selfx) - (quatX * _selfz));
        double _t11 = 2.0 * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * ((_quatx) * (_selfy) - (_quaty * _selfx));
        double _t10 = 2.0 * ((_quatz) * (_selfx) - (_quatx * _selfz));
        double _t11 = 2.0 * ((_quaty) * (_selfz) - (_quatz * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * ((quatX) * (_t0) - (quatY * _t1));
        double _t13 = 2.0 * ((quatZ) * (_t1) - (quatX * _t2));
        double _t14 = 2.0 * ((quatY) * (_t2) - (quatZ * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((quatY) * (_t12) + (((((quatW) * (_t14) + (_selfx))) - (quatZ) * (_t13)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((quatZ) * (_t14) + (((((quatW) * (_t13) + (_selfy))) - (quatX) * (_t12)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((quatX) * (_t13) + (((((quatW) * (_t12) + (_selfz))) - (quatY) * (_t14)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t0 = _selfy - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t1 = _selfx - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _t2 = _selfz - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t12 = 2.0 * java.lang.Math.fma(_quatx, _t0, -(_quaty * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(_quatz, _t1, -(_quatx * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t12, java.lang.Math.fma(-_quatz, _t13, java.lang.Math.fma(_quatw, _t14, _selfx))));
        return rotateAround_api_fma_s824a0aaf_1(dest, destOffset, _selfy, _selfz, _quatx, _quaty, _quatz, _quatw, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_s824a0aaf_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _quatx, double _quaty, double _quatz, double _quatw, double _t12, double _t13, double _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatz, _t14, java.lang.Math.fma(-_quatx, _t12, java.lang.Math.fma(_quatw, _t13, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_quatx, _t13, java.lang.Math.fma(-_quaty, _t14, java.lang.Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t0 = _selfy - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t1 = _selfx - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _t2 = _selfz - pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t12 = 2.0 * ((_quatx) * (_t0) - (_quaty * _t1));
        double _t13 = 2.0 * ((_quatz) * (_t1) - (_quatx * _t2));
        double _t14 = 2.0 * ((_quaty) * (_t2) - (_quatz * _t0));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_quaty) * (_t12) + (((((_quatw) * (_t14) + (_selfx))) - (_quatz) * (_t13)))));
        return rotateAround_api_mulAdd_s98bed38a_1(dest, destOffset, _selfy, _selfz, _quatx, _quaty, _quatz, _quatw, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_s98bed38a_1(java.lang.foreign.MemorySegment dest, long destOffset, double _selfy, double _selfz, double _quatx, double _quaty, double _quatz, double _quatw, double _t12, double _t13, double _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_quatz) * (_t14) + (((((_quatw) * (_t13) + (_selfy))) - (_quatx) * (_t12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_quatx) * (_t13) + (((((_quatw) * (_t12) + (_selfz))) - (_quaty) * (_t14)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
        double _t4 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
        double _t5 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
        double _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ))));
        return dest;
    }

    /** {@code rotateAxisAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
        double _t4 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
        double _t5 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
        double _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY)))))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return rotateAxisAround_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAxisAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t4 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        double _t5 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        double _t8 = java.lang.Math.fma(_axisz, _t4, java.lang.Math.fma(_axisx, _t3, _axisy * _t5));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisy, _t4, -(_axisz * _t5)), _t1, java.lang.Math.fma(_t2, _axisx * _t8, _pivotx))));
        return rotateAxisAround_api_fma_s9a19d42_1(dest, destOffset, _t0, _t1, _axisx, _axisy, _axisz, _pivoty, _pivotz, _t2, _t3, _t4, _t5, _t8);
    }

    /** Piece 2 of {@code rotateAxisAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_fma_s9a19d42_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _axisx, double _axisy, double _axisz, double _pivoty, double _pivotz, double _t2, double _t3, double _t4, double _t5, double _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisz, _t3, -(_axisx * _t4)), _t1, java.lang.Math.fma(_t2, _axisy * _t8, _pivoty))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisx, _t5, -(_axisy * _t3)), _t1, java.lang.Math.fma(_t2, _axisz * _t8, _pivotz))));
        return dest;
    }

    /** {@code rotateAxisAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _axisx = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisy = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisz = axis.get(_JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t4 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        double _t5 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        double _t8 = ((_axisz) * (_t4) + (((_axisx) * (_t3) + (_axisy * _t5))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t3) * (_t0) + (((((_axisy) * (_t4) - (_axisz * _t5))) * (_t1) + (((_t2) * (_axisx * _t8) + (_pivotx)))))));
        return rotateAxisAround_api_mulAdd_s491f16e1_1(dest, destOffset, _t0, _t1, _axisx, _axisy, _axisz, _pivoty, _pivotz, _t2, _t3, _t4, _t5, _t8);
    }

    /** Piece 2 of {@code rotateAxisAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd_s491f16e1_1(java.lang.foreign.MemorySegment dest, long destOffset, double _t0, double _t1, double _axisx, double _axisy, double _axisz, double _pivoty, double _pivotz, double _t2, double _t3, double _t4, double _t5, double _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t5) * (_t0) + (((((_axisz) * (_t3) - (_axisx * _t4))) * (_t1) + (((_t2) * (_axisy * _t8) + (_pivoty)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t4) * (_t0) + (((((_axisx) * (_t5) - (_axisy * _t3))) * (_t1) + (((_t2) * (_axisz * _t8) + (_pivotz)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _t9 = 2.0 * ((quatX) * (_selfz) - (quatZ * _selfx));
        double _t10 = 2.0 * ((quatY) * (_selfx) - (quatX * _selfy));
        double _t11 = 2.0 * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _quatx = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset);
        double _quaty = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 8L);
        double _quatz = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 16L);
        double _quatw = quat.get(_JAVA_DOUBLE_UNALIGNED, quatOffset + 24L);
        double _t9 = 2.0 * ((_quatx) * (_selfz) - (_quatz * _selfx));
        double _t10 = 2.0 * ((_quaty) * (_selfx) - (_quatx * _selfy));
        double _t11 = 2.0 * ((_quatz) * (_selfy) - (_quaty * _selfz));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfy) * (_t0) - (_selfz * _t1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfy) * (_t1) + (_selfz * _t0)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotY, double pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotY, double pivotZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ)));
            return dest;
        } else {
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateXAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivoty)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivotz)));
        return dest;
    }

    /** {@code rotateXAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t0) + (((_pivoty) - (_t3) * (_t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivotz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_selfz) * (_t0) - (_selfx * _t1)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotZ) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ)));
            return dest;
        } else {
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - pivotZ;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateYAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, _pivotx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, _pivotz)));
        return dest;
    }

    /** {@code rotateYAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivotz = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) - _pivotz;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (_pivotx)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, _selfy);
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, ((_t3) * (_t0) + (((_pivotz) - (_t2) * (_t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
            return dest;
        } else {
            double _selfx = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset);
            double _selfy = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_selfx) * (_t1) + (_selfy * _t0)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
            return dest;
        } else {
            double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - pivotX;
            double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - pivotY;
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
        return dest;
    }

    /** {@code rotateZAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        java.lang.foreign.ValueLayout.OfDouble _JAVA_DOUBLE_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED;
        double _selfz = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _pivotx = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(_JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset) - _pivotx;
        double _t3 = src.get(_JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) - _pivoty;
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
        dest.set(_JAVA_DOUBLE_UNALIGNED, destOffset + 16L, _selfz);
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
