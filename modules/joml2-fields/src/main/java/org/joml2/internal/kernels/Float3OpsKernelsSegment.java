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
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3Ops} and its sibling kernel units. Not public API.
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ + _selfz);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery + _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz + _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / otherZ);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, b, cZ));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, b, _cz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), bX, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, bY, cY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, bZ, cZ));
        return dest;
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _by, _cy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, scalar * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ * _selfz);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - otherY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - otherZ);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - _othery);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz - _otherz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v) {
        long _destBase = dest.address() + destOffset;
        Float3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v) {
        float _t1 = v * 6.2831855f;
        float _t2 = Math.sin(_t1);
        float _t5 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t5 * Math.cosFromSin(_t2, _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t5 * _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(2.0f, u, -1.0f));
        return dest;
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
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _vy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _vz);
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
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t7, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t6, p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) * _t2));
        return bezier_api_s74d67654_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_s74d67654_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t6, float _t7, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t0, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        return bezierTangent_api_s72e1eb9b_1(dest, destOffset, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_s72e1eb9b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t5, float _t6) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_s570c0993_1(dest, destOffset, p1Z, p2Z, p3Z, t, _selfz, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s570c0993_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p2Z, float p3Z, float t, float _selfz, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _t0 = t * t;
        return catmullRom_api_s45be6251_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s45be6251_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _p1x, float _p1y, float _p1z, float _p2x, float _p2y, float _p2z, float _p3x, float _p3y, float _p3z, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        return catmullRomTangent_api_sdfa329c8_1(dest, destOffset, t, _selfx, _selfy, _selfz, _p1x, _p1y, _p1z, _p2x, _p2y, _p2z, _p3x, _p3y, _p3z, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_sdfa329c8_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _p1x, float _p1y, float _p1z, float _p2x, float _p2y, float _p2z, float _p3x, float _p3y, float _p3z, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_api_s1a309e55_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, t * Math.fma(t, t, -t), Math.fma(t - 2.0f, _t0, t), Math.fma(3.0f, _t0, -(_t2 + _t2)), Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f)));
    }

    /** Piece 2 of {@code hermite_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_s1a309e55_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t5, float _t7, float _t9, float _t10) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t7) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t5, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t0 = t * t;
        return hermiteTangent_api_s7d4d76b8_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, 6.0f * Math.fma(t, t, -t), 6.0f * Math.fma(-t, t, t), Math.fma(3.0f, _t0, -(t + t)), Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f)));
    }

    /** Piece 2 of {@code hermiteTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_s7d4d76b8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t6, float _t7, float _t8, float _t9) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t9) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t8, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(tY, otherY - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(tZ, otherZ - _selfz, _selfz));
        return dest;
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _ty = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_s8f34d6ab_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, Math.fma(otherZ, _t12, -(_t26 * _t17)), Math.fma(otherX, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s8f34d6ab_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34) {
        float _t35 = Math.fma(otherY, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * Math.sin(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        float _t55 = _t24 * Math.cos(_t50);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = java.lang.Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
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
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * Math.sin(_t95);
        float _t100 = _t48 * Math.cos(_t95);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, otherZ - _selfz, _selfz));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        return slerp_api_s3237ebe1_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, (1.0f / (float) java.lang.Math.sqrt(_t8)), _selfx * _t9, _selfz * _t9, _selfy * _t9, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s3237ebe1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24) {
        float _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        float _t33 = Math.fma(_otherz, _t12, -(_t26 * _t17));
        float _t34 = Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * Math.sin(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        float _t55 = _t24 * Math.cos(_t50);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = java.lang.Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
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
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * Math.sin(_t95);
        float _t100 = _t48 * Math.cos(_t95);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(t, _otherz - _selfz, _selfz));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.abs(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.acos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.acos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.acos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, bX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, bY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(scalar, bZ, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, _by, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(bX, cX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(bY, cY, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(bZ, cZ, _selfz));
        return dest;
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_by, _cy, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
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
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float3OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.asin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.asin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.asin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), xX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, xY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, xZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _xy = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 4L);
        float _xz = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, _xy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cbrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cbrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cbrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ceil(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), minX), maxX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _miny = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 4L);
        float _minz = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 8L);
        float _maxy = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 4L);
        float _maxz = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset)), max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment closestPointOnLine_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - lineStartZ, Math.fma(_t1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - lineStartX, _t2 * (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t1, _t14, lineStartX));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t14, lineStartY));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, lineStartX);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, lineStartY);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, lineStartZ);
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
        float _lineStartx = lineStart.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineStartOffset);
        float _lineStarty = lineStart.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineStartOffset + 4L);
        float _lineStartz = lineStart.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineStartOffset + 8L);
        float _t0 = lineEnd.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineEndOffset + 8L) - _lineStartz;
        float _t1 = lineEnd.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineEndOffset) - _lineStartx;
        float _t2 = lineEnd.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, lineEndOffset + 4L) - _lineStarty;
        return closestPointOnLine_api_sa912501a_1(dest, destOffset, src, srcOffset, _lineStartx, _lineStarty, _lineStartz, _t0, _t1, _t2, Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** Piece 2 of {@code closestPointOnLine_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment closestPointOnLine_api_sa912501a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _lineStartx, float _lineStarty, float _lineStartz, float _t0, float _t1, float _t2, float _t10) {
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _lineStartz, Math.fma(_t1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _lineStartx, _t2 * (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t1, _t14, _lineStartx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t14, _lineStarty));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _lineStartx);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _lineStarty);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _lineStartz);
        }
        return dest;
    }

    public static float compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static float compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static float compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static float compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), signX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, signY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, signZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _signy = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 4L);
        float _signz = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, _signy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cos(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cos(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(otherX, _selfz, -(otherZ * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(otherY, _selfx, -(otherX * _selfy)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_otherx, _selfz, -(_otherz * _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toDegrees(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toDegrees(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfz));
        return dest;
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        return Math.fma(otherZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(otherX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.exp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.exp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(2.0f, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(2.0f, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(2.0f, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.expm1(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.expm1(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.expm1(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        return dest;
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 8L), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 8L), Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 4L))) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t3);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.floor(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.floor(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.floor(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, yZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 1.0f / src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 1.0f / _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 1.0f / _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        return (float) java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static float lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log10(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log10(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log10(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log1p(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log1p(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log1p(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log2(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log2(_selfz));
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
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L));
    }

    public static float manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, otherZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, otherY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, otherZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, _othery));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, yY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, yZ));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _yx = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, _yy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextDown(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextDown(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextDown(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextUp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextUp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextUp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
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

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        float _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Float3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = unitScale(normalX, normalY, normalZ);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        float _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_api_sb152cd8c_1(_selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _ct0);
    }

    /** Piece 2 of {@code orientedAngle_api}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_api_sb152cd8c_1(float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _normalx, float _normaly, float _normalz, float _t7, float _t9, float _t10, float _t11, float _ct0) {
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative() && normal.isNative()) return Float3OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Float3OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _normalBase = normal.address() + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        return orientedAngle_degenerate_api_se066be5d_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t25, _t27, _t23 * _t27, _t24 * _t27);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_se066be5d_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t25, float _t27, float _t31, float _t32) {
        float _t33 = _t25 * _t27;
        float _t40 = Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, rowX * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, rowX * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, rowY * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, rowY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, rowY * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, rowZ * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, rowZ * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, rowZ * _selfz);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _rowx = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        float _rowz = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _rowx * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _rowx * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _rowy * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _rowy * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _rowy * _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _rowz * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _rowz * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _rowz * _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment perpendicular_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfy);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfx);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfz);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, -_selfy);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponentX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponentY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponentZ));
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _exponenty = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 4L);
        float _exponentz = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, _exponenty));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ) {
        float _t7 = Math.fma(ontoZ, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(ontoX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), ontoY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t7);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t7);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t7);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        float _ontox = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _t7 = Math.fma(_ontoz, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), Math.fma(_ontox, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t7);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t7);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t7);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t2, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-normalZ, _t2, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t2, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toRadians(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toRadians(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toRadians(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = 2.0f * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-normalZ, _t3, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = 2.0f * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t3, _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float eta) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.rint(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.rint(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.rint(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.signum(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.signum(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.signum(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sin(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sin(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sinh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
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
        float _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
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
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _edgey = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 4L);
        float _edgez = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset) ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tan(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tan(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tanh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tanh(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tanh(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t19);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t14 * _t19);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t13 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Float3OpsKernelsSegment.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t19 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
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
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        return triangleNormal_degenerate_api_sc3cfe65b_1(dest, destOffset, _t52, _t53, _t54, _t55, _t43 * _t45, _t40 * _t44, Math.fma(_t52, _t53, -(_t54 * _t55)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_sc3cfe65b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t64) {
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t77 * _t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t77 * _t73);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t77 * _t71);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L) - _selfy;
        float _t1 = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L) - _selfz;
        float _t2 = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L) - _selfz;
        float _t3 = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L) - _selfy;
        float _t4 = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset) - _selfx;
        float _t5 = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset) - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsSegment.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_api_s1f534389_1(dest, destOffset, _t12, _t13, _t14, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code triangleNormal_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_api_s1f534389_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t12, float _t13, float _t14, float _t19) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t12 * _t19);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t14 * _t19);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t13 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment triangleNormal_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative()) return Float3OpsKernelsSegment.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Float3OpsKernelsSegment.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _t19 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), java.lang.Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        return triangleNormal_degenerate_api_s226b97fe_1(dest, destOffset, _p1x * _t19 - _t30, _p1y * _t19 - _t32, _p1z * _t19 - _t34, _p2y * _t19 - _t32, _p2x * _t19 - _t30, _p2z * _t19 - _t34);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment triangleNormal_degenerate_api_s226b97fe_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43) {
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _t77 * _t72);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t77 * _t73);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _t77 * _t71);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ulp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ulp(_selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ulp(_selfz));
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz0_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment xyz1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f);
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L), _selfz, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L) * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L), _selfz, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L) * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat23 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L), _selfy, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L), _selfz, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L)))));
        return preMulPositionMat3x4_api_sfaedb9a6_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat11, _mat12, _mat13, _mat20, _mat21, _mat22, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat3x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat3x4_api_sfaedb9a6_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat11, float _mat12, float _mat13, float _mat20, float _mat21, float _mat22, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L), _selfy, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 48L)))));
        return preMulPositionMat4x4_api_sa7b588f3_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulPositionMat4x4_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulPositionMat4x4_api_sa7b588f3_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMulProject_api_s200a5ffb_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMulProject_api_s200a5ffb_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23) {
        float _t2_inv = 1.0f / Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 28L), _selfy, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 44L), _selfz, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 60L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L), _selfy, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 48L)))) * _t2_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz))));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t0 = _selfy - pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t1 = _selfx - pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _t2 = _selfz - pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t12 = 2.0f * Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx))));
        return rotateAround_api_s411d764a_1(dest, destOffset, _selfy, _selfz, _quatx, _quaty, _quatz, _quatw, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code rotateAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAround_api_s411d764a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfy, float _selfz, float _quatx, float _quaty, float _quatz, float _quatw, float _t12, float _t13, float _t14) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxisAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
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
        float _t0 = Math.sin(angle);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx))));
        return rotateAxisAround_api_se1124112_1(dest, destOffset, _t0, _axisx, _axisy, _axisz, _pivoty, _pivotz, _t1, _t2, _t3, _t4, _t5, _t8);
    }

    /** Piece 2 of {@code rotateAxisAround_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxisAround_api_se1124112_1(java.lang.foreign.MemorySegment dest, long destOffset, float _t0, float _axisx, float _axisy, float _axisz, float _pivoty, float _pivotz, float _t1, float _t2, float _t3, float _t4, float _t5, float _t8) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
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
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _quatx = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfy, _t0, _selfz * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotY, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateXAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotZ) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - pivotZ;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateYAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _pivotz;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
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
