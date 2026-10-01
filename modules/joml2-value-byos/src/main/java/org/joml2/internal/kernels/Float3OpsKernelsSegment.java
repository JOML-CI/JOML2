// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3OpsKernelsSegment {
    private Float3OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, otherX + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY + _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ + _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery + _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz + _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / scalar);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / scalar);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / otherX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / otherY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / other.get(_JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / _othery);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b, cX));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, b, cY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, b, cZ));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b) + (cX)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (b) + (cY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (b) + (cZ)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b, c.get(_JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, b, _cy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, b, _cz));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b) + (c.get(_JAVA_FLOAT_UNALIGNED, cOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (b) + (_cy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (b) + (_cz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), bX, cX));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, bY, cY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, bZ, cZ));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (bX) + (cX)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (bY) + (cY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (bZ) + (cZ)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b.get(_JAVA_FLOAT_UNALIGNED, bOffset), c.get(_JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _by, _cy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) + (c.get(_JAVA_FLOAT_UNALIGNED, cOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_by) + (_cy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_bz) + (_cz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, scalar * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, otherX * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, -src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - otherY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - _othery);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v) {
        long _destBase = dest.address() + destOffset;
        Float3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t1 = v * 6.2831855f;
            float _t4 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t4 * Math.cosWithSin(_t1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4 * Math.sinWithCos(_t1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(2.0f, u, -1.0f));
            return dest;
        } else {
            float _t1 = v * 6.2831855f;
            float _t4 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t4 * Math.cosWithSin(_t1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t4 * Math.sinWithCos(_t1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((2.0f) * (u) - (1.0f)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ) {
        long _destBase = dest.address() + destOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, vY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, vZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, v.get(_JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _vy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _vz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p1X, _t7, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p1X) * (_t7) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset), _t7, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset), _t6, p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) * _t2));
        return bezier_api_fma_s58540a0d_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma_s58540a0d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t6, float _t7, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset)) * (_t7) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8)) + ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset)) * (_t6) + (p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) * _t2)));
        return bezier_api_mulAdd_sb662e2f6_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd_sb662e2f6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t6, float _t7, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset), _t0, java.lang.Math.fma(p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset), _t3, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset)) * (_t0) + (((p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset)) * (_t3) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t2) + ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        return bezierTangent_api_fma_s6906978e_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma_s6906978e_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t5, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        return bezierTangent_api_mulAdd_sa088275b_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd_sa088275b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t5, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_fma_s207af242_1(dest, destOffset, p1Z, p2Z, p3Z, t, _selfz, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s207af242_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p2Z, float p3Z, float t, float _selfz, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = t * t;
        return catmullRom_api_fma_s5a104abc_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s5a104abc_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _p1x, float _p1y, float _p1z, float _p2x, float _p2y, float _p2z, float _p3x, float _p3y, float _p3z, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = t * t;
        return catmullRom_api_mulAdd_sae34f041_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sae34f041_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _p1x, float _p1y, float _p1z, float _p2x, float _p2y, float _p2z, float _p3x, float _p3y, float _p3z, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        return catmullRomTangent_api_fma_scc3087d9_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_scc3087d9_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _p1x, float _p1y, float _p1z, float _p2x, float _p2y, float _p2z, float _p3x, float _p3y, float _p3z, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = t * t;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_api_mulAdd_s6be7c462_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s6be7c462_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_api_fma_sa52a792c_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0f, _t0, t), java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2)), java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f)));
    }

    /** Piece 2 of {@code hermite_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma_sa52a792c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t5, float _t7, float _t9, float _t10) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t7) + java.lang.Math.fma(t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset), _t5, v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_api_mulAdd_sbd0e894d_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, t * ((t) * (t) - (t)), ((t - 2.0f) * (_t0) + (t)), ((3.0f) * (_t0) - (_t2 + _t2)), ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0)))));
    }

    /** Piece 2 of {@code hermite_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd_sbd0e894d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t5, float _t7, float _t9, float _t10) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t10) + (t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t7)) + ((t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset)) * (_t5) + (v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        return hermiteTangent_api_fma_s18059f1d_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, 6.0f * java.lang.Math.fma(t, t, -t), 6.0f * java.lang.Math.fma(-t, t, t), java.lang.Math.fma(3.0f, _t0, -(t + t)), java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f)));
    }

    /** Piece 2 of {@code hermiteTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma_s18059f1d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t6, float _t7, float _t8, float _t9) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t9) + java.lang.Math.fma(t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset), _t8, v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        return hermiteTangent_api_mulAdd_sf5aac082_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, 6.0f * ((t) * (t) - (t)), 6.0f * ((t) - (t) * (t)), ((3.0f) * (_t0) - (t + t)), ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t)))));
    }

    /** Piece 2 of {@code hermiteTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd_sf5aac082_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t6, float _t7, float _t8, float _t9) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + (t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t9)) + ((t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset)) * (_t8) + (v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((tY) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _tBase = t.address() + tOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t.get(_JAVA_FLOAT_UNALIGNED, tOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t.get(_JAVA_FLOAT_UNALIGNED, tOffset)) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_fma_se2b6abdc_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_se2b6abdc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34) {
        float _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_api_mulAdd_sa8d78c4b_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sa8d78c4b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        return slerp_api_fma_sd47cabcc_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, (1.0f / (float) java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sd47cabcc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24) {
        float _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        float _t33 = java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17));
        float _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        return slerp_api_mulAdd_s67967b81_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, (1.0f / (float) java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s67967b81_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24) {
        float _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        float _t33 = ((_otherz) * (_t12) - (_t26 * _t17));
        float _t34 = ((_otherx) * (_t12) - (_t26 * _t14));
        float _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_fma_s3750ef60_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s3750ef60_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33) {
        float _t34 = java.lang.Math.fma(otherX, _t12, -(_t26 * _t14));
        float _t35 = java.lang.Math.fma(otherY, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_api_mulAdd_sca22bdb1_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sca22bdb1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34) {
        float _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        return slerp_api_fma_s37ad4c10_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, (1.0f / (float) java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s37ad4c10_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24) {
        float _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        float _t33 = java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17));
        float _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        return slerp_api_mulAdd_sf31643e7_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, (1.0f / (float) java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sf31643e7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24) {
        float _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        float _t33 = ((_otherz) * (_t12) - (_t26 * _t17));
        float _t34 = ((_otherx) * (_t12) - (_t26 * _t14));
        float _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.abs(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.acos(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.acos(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.acos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(scalar, bX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(scalar, bY, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, bZ, _selfz));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((scalar) * (bX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((scalar) * (bY) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((scalar) * (bZ) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(scalar, b.get(_JAVA_FLOAT_UNALIGNED, bOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(scalar, _by, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((scalar) * (b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((scalar) * (_by) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((scalar) * (_bz) + (_selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(bX, cX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(bY, cY, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(bZ, cZ, _selfz));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((bX) * (cX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((bY) * (cY) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((bZ) * (cZ) + (_selfz)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(b.get(_JAVA_FLOAT_UNALIGNED, bOffset), c.get(_JAVA_FLOAT_UNALIGNED, cOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_by, _cy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) * (c.get(_JAVA_FLOAT_UNALIGNED, cOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_by) * (_cy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_bz) * (_cz) + (_selfz)));
        return dest;
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = otherY * -_selfz;
        float _t4 = -(otherX * _selfy);
        float _t5 = otherX * -_selfz;
        float _t12 = java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3);
        float _t13 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        float _t14 = java.lang.Math.fma(otherZ, _selfx, _t5) - java.lang.Math.fma(otherX, _selfz, _t5);
        float _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = otherY * -_selfz;
        float _t4 = -(otherX * _selfy);
        float _t5 = otherX * -_selfz;
        float _t12 = ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3));
        float _t13 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        float _t14 = ((otherZ) * (_selfx) + (_t5)) - ((otherX) * (_selfz) + (_t5));
        float _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t3 = _othery * -_selfz;
        float _t4 = -(_otherx * _selfy);
        float _t5 = _otherx * -_selfz;
        float _t12 = java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3);
        float _t13 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        float _t14 = java.lang.Math.fma(_otherz, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfz, _t5);
        float _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t3 = _othery * -_selfz;
        float _t4 = -(_otherx * _selfy);
        float _t5 = _otherx * -_selfz;
        float _t12 = ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3));
        float _t13 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        float _t14 = ((_otherz) * (_selfx) + (_t5)) - ((_otherx) * (_selfz) + (_t5));
        float _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        return angleBetween_degenerate_api_fma_s753ecfb1_1(_t8, _t9, _t10, _t11, _t12, _t13, _t29, _t26 * _t29, _t27 * _t29, _t28 * _t29);
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_fma_s753ecfb1_1(float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t29, float _t33, float _t34, float _t35) {
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        return angleBetween_degenerate_api_mulAdd_s987d4298_1(_t8, _t9, _t10, _t11, _t12, _t13, _t28, _t29, _t26 * _t29, _t27 * _t29);
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd_s987d4298_1(float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t28, float _t29, float _t33, float _t34) {
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.asin(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.asin(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.asin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), x));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, x));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), xX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, xY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _xBase = x.address() + xOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _xy = x.get(_JAVA_FLOAT_UNALIGNED, xOffset + 4L);
        float _xz = x.get(_JAVA_FLOAT_UNALIGNED, xOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), x.get(_JAVA_FLOAT_UNALIGNED, xOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, _xy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cbrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cbrt(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cbrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.ceil(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ceil(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), min), max));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, min), max));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), minX), maxX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, minY), maxY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _minBase = min.address() + minOffset;
        long _maxBase = max.address() + maxOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _miny = min.get(_JAVA_FLOAT_UNALIGNED, minOffset + 4L);
        float _minz = min.get(_JAVA_FLOAT_UNALIGNED, minOffset + 8L);
        float _maxy = max.get(_JAVA_FLOAT_UNALIGNED, maxOffset + 4L);
        float _maxz = max.get(_JAVA_FLOAT_UNALIGNED, maxOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), min.get(_JAVA_FLOAT_UNALIGNED, minOffset)), max.get(_JAVA_FLOAT_UNALIGNED, maxOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_api_mulAdd(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t14 = Math.max(0.0f, Math.min(1.0f, java.lang.Math.fma(_t0, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - lineStartZ, java.lang.Math.fma(_t1, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - lineStartX, _t2 * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t1, _t14, lineStartX));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t14, lineStartY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, lineStartX);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, lineStartY);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, lineStartZ);
        }
        return dest;
    }

    /** {@code closestPointOnLine_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t14 = Math.max(0.0f, Math.min(1.0f, ((_t0) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - lineStartZ) + (((_t1) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - lineStartX) + (_t2 * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - lineStartY))))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t1) * (_t14) + (lineStartX)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t14) + (lineStartY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t0) * (_t14) + (lineStartZ)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, lineStartX);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, lineStartY);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, lineStartZ);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _lineStartBase = lineStart.address() + lineStartOffset;
        long _lineEndBase = lineEnd.address() + lineEndOffset;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_api_fma(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return closestPointOnLine_api_mulAdd(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@code closestPointOnLine_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _lineStartx = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset);
        float _lineStarty = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset + 4L);
        float _lineStartz = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset + 8L);
        float _t0 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset + 8L) - _lineStartz;
        float _t1 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset) - _lineStartx;
        float _t2 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset + 4L) - _lineStarty;
        return closestPointOnLine_api_fma_s75c412e7_1(dest, destOffset, src, srcOffset, _lineStartx, _lineStarty, _lineStartz, _t0, _t1, _t2, java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** Piece 2 of {@code closestPointOnLine_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_fma_s75c412e7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _lineStartx, float _lineStarty, float _lineStartz, float _t0, float _t1, float _t2, float _t10) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t14 = Math.max(0.0f, Math.min(1.0f, java.lang.Math.fma(_t0, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _lineStartz, java.lang.Math.fma(_t1, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _lineStartx, _t2 * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t1, _t14, _lineStartx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t14, _lineStarty));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _lineStartx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _lineStarty);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _lineStartz);
        }
        return dest;
    }

    /** {@code closestPointOnLine_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment lineStart, long lineStartOffset, java.lang.foreign.MemorySegment lineEnd, long lineEndOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _lineStartx = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset);
        float _lineStarty = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset + 4L);
        float _lineStartz = lineStart.get(_JAVA_FLOAT_UNALIGNED, lineStartOffset + 8L);
        float _t0 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset + 8L) - _lineStartz;
        float _t1 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset) - _lineStartx;
        float _t2 = lineEnd.get(_JAVA_FLOAT_UNALIGNED, lineEndOffset + 4L) - _lineStarty;
        return closestPointOnLine_api_mulAdd_s7785852_1(dest, destOffset, src, srcOffset, _lineStartx, _lineStarty, _lineStartz, _t0, _t1, _t2, ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
    }

    /** Piece 2 of {@code closestPointOnLine_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_mulAdd_s7785852_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _lineStartx, float _lineStarty, float _lineStartz, float _t0, float _t1, float _t2, float _t10) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t14 = Math.max(0.0f, Math.min(1.0f, ((_t0) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _lineStartz) + (((_t1) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _lineStartx) + (_t2 * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _lineStarty))))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t1) * (_t14) + (_lineStartx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t14) + (_lineStarty)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t0) * (_t14) + (_lineStartz)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _lineStartx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _lineStarty);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _lineStartz);
        }
        return dest;
    }

    public static float compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static float compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return Math.max(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static float compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return Math.min(Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static float compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), sign));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, sign));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), signX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, signY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _signBase = sign.address() + signOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _signy = sign.get(_JAVA_FLOAT_UNALIGNED, signOffset + 4L);
        float _signz = sign.get(_JAVA_FLOAT_UNALIGNED, signOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), sign.get(_JAVA_FLOAT_UNALIGNED, signOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, _signy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cos(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cos(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cosh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = -(otherY * _selfz);
        float _t4 = -(otherZ * _selfx);
        float _t5 = -(otherX * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(otherX, _selfz, _t4) - java.lang.Math.fma(otherZ, _selfx, _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(otherY, _selfx, _t5) - java.lang.Math.fma(otherX, _selfy, _t5));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = -(otherY * _selfz);
        float _t4 = -(otherZ * _selfx);
        float _t5 = -(otherX * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((otherX) * (_selfz) + (_t4)) - ((otherZ) * (_selfx) + (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((otherY) * (_selfx) + (_t5)) - ((otherX) * (_selfy) + (_t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t3 = -(_othery * _selfz);
        float _t4 = -(_otherz * _selfx);
        float _t5 = -(_otherx * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_otherx, _selfz, _t4) - java.lang.Math.fma(_otherz, _selfx, _t4));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_othery, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfy, _t5));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t3 = -(_othery * _selfz);
        float _t4 = -(_otherz * _selfx);
        float _t5 = -(_otherx * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_otherx) * (_selfz) + (_t4)) - ((_otherz) * (_selfx) + (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_othery) * (_selfx) + (_t5)) - ((_otherx) * (_selfy) + (_t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.toDegrees(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toDegrees(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfz));
        return dest;
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(otherX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        } else {
            return ((otherZ) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((otherX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (otherY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))));
        }
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(other.get(_JAVA_FLOAT_UNALIGNED, otherOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
        } else {
            return ((other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))));
        }
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.exp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.exp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.exp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(2.0f, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(2.0f, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(2.0f, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.expm1(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.expm1(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.expm1(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0f ? 1.0f : -1.0f;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = java.lang.Math.fma(I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 8L), Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 8L), java.lang.Math.fma(I.get(_JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 4L))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = ((I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 8L)) * (Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 8L)) + (((I.get(_JAVA_FLOAT_UNALIGNED, IOffset)) * (Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset)) + (I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 4L))))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.floor(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.floor(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.floor(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), yX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, yY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _yy = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), y.get(_JAVA_FLOAT_UNALIGNED, yOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, _yy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f / src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 1.0f / _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 1.0f / _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return (float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        }
    }

    public static float lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            return ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        }
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log10(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log10(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log10(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log1p(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log1p(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log1p(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log2(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log2(_selfz));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ);
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L));
    }

    public static float manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, otherY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, _othery));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, otherY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, _othery));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, yY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _yx = y.get(_JAVA_FLOAT_UNALIGNED, yOffset);
        float _yy = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, _yy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.nextDown(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextDown(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextDown(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.nextUp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextUp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextUp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
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

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalize_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
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

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_api_mulAdd(dest, destOffset, src, srcOffset, length);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizeMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t4 = -(otherX * _selfy);
        float _t5 = -(otherZ * _selfx);
        float _t6 = -(otherY * _selfz);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t16 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        float _t17 = java.lang.Math.fma(otherX, _selfz, _t5) - java.lang.Math.fma(otherZ, _selfx, _t5);
        float _t18 = java.lang.Math.fma(otherZ, _selfy, _t6) - java.lang.Math.fma(otherY, _selfz, _t6);
        float _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Float3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0f ? -_t24 : _t24;
    }

    /** {@code orientedAngle_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t4 = -(otherX * _selfy);
        float _t5 = -(otherZ * _selfx);
        float _t6 = -(otherY * _selfz);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t16 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        float _t17 = ((otherX) * (_selfz) + (_t5)) - ((otherZ) * (_selfx) + (_t5));
        float _t18 = ((otherZ) * (_selfy) + (_t6)) - ((otherY) * (_selfz) + (_t6));
        float _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Float3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0f ? -_t24 : _t24;
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_api_fma_sfb1d1173_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_fma_sfb1d1173_1(float normalX, float normalY, float normalZ, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t33, float _t39, float _t40, float _t41) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        float _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_api_mulAdd_s94f7392a_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_mulAdd_s94f7392a_1(float normalX, float normalY, float normalZ, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t33, float _t39, float _t40, float _t41) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t4 = -(_otherx * _selfy);
        float _t5 = -(_otherz * _selfx);
        float _t6 = -(_othery * _selfz);
        float _t16 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        float _t17 = java.lang.Math.fma(_otherx, _selfz, _t5) - java.lang.Math.fma(_otherz, _selfx, _t5);
        float _t18 = java.lang.Math.fma(_otherz, _selfy, _t6) - java.lang.Math.fma(_othery, _selfz, _t6);
        return orientedAngle_api_fma_s4f0a2cc7_1(src, srcOffset, other, otherOffset, normal, normalOffset, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18)));
    }

    /** Piece 2 of {@code orientedAngle_api_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_api_fma_s4f0a2cc7_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _normalx, float _normaly, float _normalz, float _t7, float _t16, float _t17, float _t18, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
        return java.lang.Math.fma(_t16, _normalz * _t7, java.lang.Math.fma(_t17, _normaly * _t7, _t18 * (_normalx * _t7))) < 0.0f ? -_t24 : _t24;
    }

    /** {@code orientedAngle_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t4 = -(_otherx * _selfy);
        float _t5 = -(_otherz * _selfx);
        float _t6 = -(_othery * _selfz);
        float _t16 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        float _t17 = ((_otherx) * (_selfz) + (_t5)) - ((_otherz) * (_selfx) + (_t5));
        float _t18 = ((_otherz) * (_selfy) + (_t6)) - ((_othery) * (_selfz) + (_t6));
        return orientedAngle_api_mulAdd_s3cac592_1(src, srcOffset, other, otherOffset, normal, normalOffset, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18)))));
    }

    /** Piece 2 of {@code orientedAngle_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_api_mulAdd_s3cac592_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _normalx, float _normaly, float _normalz, float _t7, float _t16, float _t17, float _t18, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
        return ((_t16) * (_normalz * _t7) + (((_t17) * (_normaly * _t7) + (_t18 * (_normalx * _t7))))) < 0.0f ? -_t24 : _t24;
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative() && normal.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_api_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_degenerate_api_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        return orientedAngle_degenerate_api_fma_sa5b73ac6_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, -(_t13 * _t10), java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19), java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_fma_sa5b73ac6_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t21, float _t30, float _t31) {
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        float _t33 = unitScale(_t31, _t32, _t30);
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        return orientedAngle_degenerate_api_mulAdd_s970978d_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, -(_t13 * _t10), ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19)), ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20)));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_mulAdd_s970978d_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t21, float _t30, float _t31) {
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, rowX * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, rowX * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, rowY * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, rowY * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, rowY * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, rowZ * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, rowZ * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, rowZ * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rowBase = row.address() + rowOffset;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _rowx = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        float _rowz = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _rowx * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _rowx * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _rowy * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _rowy * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _rowy * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _rowz * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _rowz * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _rowz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfx);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfz);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfy);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponent));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponent));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponentX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponentY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _exponentBase = exponent.address() + exponentOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _exponenty = exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset + 4L);
        float _exponentz = exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, _exponenty));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t7 = java.lang.Math.fma(ontoZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(ontoX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), ontoY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t7);
            return dest;
        } else {
            float _t7 = ((ontoZ) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((ontoX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (ontoY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t7);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t7);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _ontox = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _t7 = java.lang.Math.fma(_ontoz, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(_ontox, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))) / java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t7);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t7);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t7);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _ontox = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _t7 = ((_ontoz) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((_ontox) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_ontoy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))) / ((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t7);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t7);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t7);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t2 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t2, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-normalY, _t2, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalZ, _t2, _selfz));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t2 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t2)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (normalY) * (_t2)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (normalZ) * (_t2)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t2 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t2, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_normaly, _t2, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t2 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (_normaly) * (_t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (_normalz) * (_t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.toRadians(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toRadians(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toRadians(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t3 = 2.0f * java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-normalY, _t3, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalZ, _t3, _selfz));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t3 = 2.0f * ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t3)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (normalY) * (_t3)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (normalZ) * (_t3)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = 2.0f * java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = 2.0f * ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (_normaly) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (_normalz) * (_t3)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0f), eta * eta, 1.0f);
        float _t10 = java.lang.Math.fma(eta, _t3, (float) java.lang.Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
        float _t7 = ((((_t3) * (_t3) - (1.0f))) * (eta * eta) + (1.0f));
        float _t10 = ((eta) * (_t3) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t7))));
        if (_t7 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((eta) * (_selfx) - (normalX * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((eta) * (_selfy) - (normalY * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((eta) * (_selfz) - (normalZ * _t10)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0f), eta * eta, 1.0f);
        return refract_api_s3d8b998e_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _normalx, _normaly, _normalz, _t7, java.lang.Math.fma(eta, _t3, (float) java.lang.Math.sqrt(Math.max(0.0f, _t7))));
    }

    /** Piece 2 of {@code refract_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_s3d8b998e_1(java.lang.foreign.MemorySegment dest, long destOffset, float eta, float _selfx, float _selfy, float _selfz, float _normalx, float _normaly, float _normalz, float _t7, float _t10) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t7 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        float _t7 = ((((_t3) * (_t3) - (1.0f))) * (eta * eta) + (1.0f));
        float _t10 = ((eta) * (_t3) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t7))));
        if (_t7 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((eta) * (_selfx) - (_normalx * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((eta) * (_selfy) - (_normaly * _t10)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((eta) * (_selfz) - (_normalz * _t10)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.rint(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.rint(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.rint(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.signum(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.signum(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.signum(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.sin(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sin(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.sinh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t10 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t10 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t10)) * _t10 * _t10);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t11)) * _t11 * _t11);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edge0Base = edge0.address() + edge0Offset;
        long _edge1Base = edge1.address() + edge1Offset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _edge0x = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _edge0x = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(_JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edge ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edge ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edgeBase = edge.address() + edgeOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _edgey = edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset + 4L);
        float _edgez = edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset) ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.tan(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tan(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.tanh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tanh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tanh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        float _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        float _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        float _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        float _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        float _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        float _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t25);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t57 = _t40 * _t44;
        return triangleNormal_degenerate_api_fma_s10927fee_1(dest, destOffset, _t38 * _t44, _t53, _t54, _t55, _t43 * _t45, _t57, -(_t54 * _t55), -(_t57 * _t53));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s10927fee_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61, float _t62) {
        float _t63 = -(_t52 * _t56);
        float _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t82 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t83 * _t79);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t83 * _t77);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t57 = _t40 * _t44;
        return triangleNormal_degenerate_api_mulAdd_s98434aa3_1(dest, destOffset, _t38 * _t44, _t53, _t54, _t55, _t43 * _t45, _t57, -(_t54 * _t55), -(_t57 * _t53));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd_s98434aa3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61, float _t62) {
        float _t63 = -(_t52 * _t56);
        float _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t82 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t83 * _t79);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t83 * _t77);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L) - _selfy;
        float _t1 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L) - _selfz;
        float _t2 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L) - _selfz;
        float _t3 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L) - _selfy;
        float _t4 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset) - _selfx;
        float _t5 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        float _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        float _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        return triangleNormal_api_fma_sc735fdb0_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, _t18, _t19, _t20, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20)));
    }

    /** Piece 2 of {@code triangleNormal_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_fma_sc735fdb0_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float _t18, float _t19, float _t20, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L) - _selfy;
        float _t1 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L) - _selfz;
        float _t2 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L) - _selfz;
        float _t3 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L) - _selfy;
        float _t4 = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset) - _selfx;
        float _t5 = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        float _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        float _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        return triangleNormal_api_mulAdd_s6b6c8b0b_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, _t18, _t19, _t20, ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20)))));
    }

    /** Piece 2 of {@code triangleNormal_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_mulAdd_s6b6c8b0b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float _t18, float _t19, float _t20, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t18 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t20 * _t25);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t19 * _t25);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative()) return Float3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        return triangleNormal_degenerate_api_fma_s45e8ab9b_1(dest, destOffset, _p1x * _t19 - _t30, _p1y * _t19 - _t32, _p1z * _t19 - _t34, _p2y * _t19 - _t32, _p2x * _t19 - _t30, _p2z * _t19 - _t34);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s45e8ab9b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43) {
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        return triangleNormal_degenerate_api_fma_s45e8ab9b_2(dest, destOffset, _t77, _t78, _t79, _t82, (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code triangleNormal_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_fma_s45e8ab9b_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t77, float _t78, float _t79, float _t82, float _t83) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t82 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t83 * _t79);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t83 * _t77);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        return triangleNormal_degenerate_api_mulAdd_sf0404f46_1(dest, destOffset, _p1x * _t19 - _t30, _p1y * _t19 - _t32, _p1z * _t19 - _t34, _p2y * _t19 - _t32, _p2x * _t19 - _t30, _p2z * _t19 - _t34);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd_sf0404f46_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43) {
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        return triangleNormal_degenerate_api_mulAdd_sf0404f46_2(dest, destOffset, _t77, _t78, _t79, _t82, (1.0f / (float) java.lang.Math.sqrt(_t82)));
    }

    /** Piece 3 of {@code triangleNormal_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_mulAdd_sf0404f46_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t77, float _t78, float _t79, float _t82, float _t83) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t82 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _t83 * _t78);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t83 * _t79);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t83 * _t77);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.ulp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ulp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ulp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L), _selfz, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L) * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L)) * (_selfz) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L) * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat3x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulDirectionMat3x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulDirectionMat3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L), _selfz, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L) * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat3x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L)) * (_selfz) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L) * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat4x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulDirectionMat4x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulDirectionMat4x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat4x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulDirectionMat4x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L)) * (_selfz) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulPositionMat3x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulPositionMat3x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L), _selfy, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L), _selfz, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L)))));
        return preMulPositionMat3x4_api_fma_s5fb2a05d_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat11, _mat12, _mat13, _mat20, _mat21, _mat22, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat3x4_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_fma_s5fb2a05d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat11, float _mat12, float _mat13, float _mat20, float _mat21, float _mat22, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat3x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L)) * (_selfy) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L)) * (_selfz) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L))))))));
        return preMulPositionMat3x4_api_mulAdd_sbdf969a4_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat11, _mat12, _mat13, _mat20, _mat21, _mat22, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat3x4_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_mulAdd_sbdf969a4_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat11, float _mat12, float _mat13, float _mat20, float _mat21, float _mat22, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulPositionMat4x4_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulPositionMat4x4_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L), _selfy, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L)))));
        return preMulPositionMat4x4_api_fma_s105fb8f8_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat4x4_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_fma_s105fb8f8_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat4x4_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L)) * (_selfy) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L)) * (_selfz) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L))))))));
        return preMulPositionMat4x4_api_mulAdd_sb9b90de3_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat4x4_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_mulAdd_sb9b90de3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulProject_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulProject_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulProject_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulProject_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulProject_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMulProject_api_fma_seb2505e0_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_fma_seb2505e0_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2_inv = 1.0f / java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L), _selfx, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L), _selfy, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L), _selfz, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 60L))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L), _selfy, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L)))) * _t2_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    /** {@code preMulProject_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMulProject_api_mulAdd_s8c5f32b_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_mulAdd_s8c5f32b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2_inv = 1.0f / ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L)) * (_selfx) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L)) * (_selfy) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L)) * (_selfz) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 60L)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L)) * (_selfy) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L)) * (_selfz) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L))))))) * _t2_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))) * _t2_inv);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))) * _t2_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * ((_quatx) * (_selfy) - (_quaty * _selfx));
        float _t10 = 2.0f * ((_quatz) * (_selfx) - (_quatx * _selfz));
        float _t11 = 2.0f * ((_quaty) * (_selfz) - (_quatz * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * ((quatX) * (_t0) - (quatY * _t1));
        float _t13 = 2.0f * ((quatZ) * (_t1) - (quatX * _t2));
        float _t14 = 2.0f * ((quatY) * (_t2) - (quatZ * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((quatY) * (_t12) + (((((quatW) * (_t14) + (_selfx))) - (quatZ) * (_t13)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((quatZ) * (_t14) + (((((quatW) * (_t13) + (_selfy))) - (quatX) * (_t12)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((quatX) * (_t13) + (((((quatW) * (_t12) + (_selfz))) - (quatY) * (_t14)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@code rotateAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t0 = _selfy - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t1 = _selfx - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _t2 = _selfz - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t12 = 2.0f * java.lang.Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t12, java.lang.Math.fma(-_quatz, _t13, java.lang.Math.fma(_quatw, _t14, _selfx))));
        return rotateAround_api_fma_s824a0aaf_1(dest, destOffset, _selfy, _selfz, _quatx, _quaty, _quatz, _quatw, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code rotateAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_fma_s824a0aaf_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _quatx, float _quaty, float _quatz, float _quatw, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_quatz, _t14, java.lang.Math.fma(-_quatx, _t12, java.lang.Math.fma(_quatw, _t13, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatx, _t13, java.lang.Math.fma(-_quaty, _t14, java.lang.Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t0 = _selfy - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t1 = _selfx - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _t2 = _selfz - pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t12 = 2.0f * ((_quatx) * (_t0) - (_quaty * _t1));
        float _t13 = 2.0f * ((_quatz) * (_t1) - (_quatx * _t2));
        float _t14 = 2.0f * ((_quaty) * (_t2) - (_quatz * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_quaty) * (_t12) + (((((_quatw) * (_t14) + (_selfx))) - (_quatz) * (_t13)))));
        return rotateAround_api_mulAdd_s98bed38a_1(dest, destOffset, _selfy, _selfz, _quatx, _quaty, _quatz, _quatw, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code rotateAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_mulAdd_s98bed38a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _quatx, float _quaty, float _quatz, float _quatw, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_quatz) * (_t14) + (((((_quatw) * (_t13) + (_selfy))) - (_quatx) * (_t12)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_quatx) * (_t13) + (((((_quatw) * (_t12) + (_selfz))) - (_quaty) * (_t14)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        float _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ))));
        return dest;
    }

    /** {@code rotateAxisAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        float _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _axisBase = axis.address() + axisOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return rotateAxisAround_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@code rotateAxisAround_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t8 = java.lang.Math.fma(_axisz, _t4, java.lang.Math.fma(_axisx, _t3, _axisy * _t5));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisy, _t4, -(_axisz * _t5)), _t1, java.lang.Math.fma(_t2, _axisx * _t8, _pivotx))));
        return rotateAxisAround_api_fma_s9f936b59_1(dest, destOffset, _t0, _t1, _axisx, _axisy, _axisz, _pivoty, _pivotz, _t2, _t3, _t4, _t5, _t8);
    }

    /** Piece 2 of {@code rotateAxisAround_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_fma_s9f936b59_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _axisx, float _axisy, float _axisz, float _pivoty, float _pivotz, float _t2, float _t3, float _t4, float _t5, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisz, _t3, -(_axisx * _t4)), _t1, java.lang.Math.fma(_t2, _axisy * _t8, _pivoty))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisx, _t5, -(_axisy * _t3)), _t1, java.lang.Math.fma(_t2, _axisz * _t8, _pivotz))));
        return dest;
    }

    /** {@code rotateAxisAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t8 = ((_axisz) * (_t4) + (((_axisx) * (_t3) + (_axisy * _t5))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t3) * (_t0) + (((((_axisy) * (_t4) - (_axisz * _t5))) * (_t1) + (((_t2) * (_axisx * _t8) + (_pivotx)))))));
        return rotateAxisAround_api_mulAdd_sce352080_1(dest, destOffset, _t0, _t1, _axisx, _axisy, _axisz, _pivoty, _pivotz, _t2, _t3, _t4, _t5, _t8);
    }

    /** Piece 2 of {@code rotateAxisAround_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_mulAdd_sce352080_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _t1, float _axisx, float _axisy, float _axisz, float _pivoty, float _pivotz, float _t2, float _t3, float _t4, float _t5, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t5) * (_t0) + (((((_axisz) * (_t3) - (_axisx * _t4))) * (_t1) + (((_t2) * (_axisy * _t8) + (_pivoty)))))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t4) * (_t0) + (((((_axisx) * (_t5) - (_axisy * _t3))) * (_t1) + (((_t2) * (_axisz * _t8) + (_pivotz)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _quatBase = quat.address() + quatOffset;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * ((_quatx) * (_selfz) - (_quatz * _selfx));
        float _t10 = 2.0f * ((_quaty) * (_selfx) - (_quatx * _selfy));
        float _t11 = 2.0f * ((_quatz) * (_selfy) - (_quaty * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t0) - (_selfz * _t1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t1) + (_selfz * _t0)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotY, float pivotZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ)));
            return dest;
        } else {
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateXAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivoty)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivotz)));
        return dest;
    }

    /** {@code rotateXAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateXAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t0) + (((_pivoty) - (_t3) * (_t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivotz)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t0) - (_selfx * _t1)));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ)));
            return dest;
        } else {
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateYAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, _pivotx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, _pivotz)));
        return dest;
    }

    /** {@code rotateYAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateYAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivotz = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (_pivotx)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t3) * (_t0) + (((_pivotz) - (_t2) * (_t1)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t1) + (_selfy * _t0)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            return dest;
        } else {
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        return dest;
    }

    /** {@code rotateZAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZAround_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _pivotx = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(_JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
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
