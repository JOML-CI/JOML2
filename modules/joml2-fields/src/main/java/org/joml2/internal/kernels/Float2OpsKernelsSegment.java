// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2OpsKernelsSegment {
    private Float2OpsKernelsSegment() {}

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY + _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery + _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / scalar);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) / other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / _othery);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _cBase = c.address() + cOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b, c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float cX, float cY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), bX, cX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, scalar * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, scalar * _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, otherX * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY * _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery * _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, -_selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy - _othery);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u) {
        long _destBase = dest.address() + destOffset;
        Float2OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, float u) {
        float _t0 = u * 6.2831855f;
        float _t1 = Math.sin(_t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosFromSin(_t1, _t0));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, vX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, vY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _vy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        long _destBase = dest.address() + destOffset;
        Float2OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, s);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, s);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_unsafe(java.lang.foreign.MemorySegment dest, long destOffset) {
        long _destBase = dest.address() + destOffset;
        Float2OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero_api(java.lang.foreign.MemorySegment dest, long destOffset) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1X, _t7, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t7, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t6, p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) * _t2));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset), _t0, Math.fma(p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_api_s45be6251_1(dest, destOffset, t, _selfy, _p1y, _p2y, _p3y, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_s45be6251_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfy, float _p1y, float _p2y, float _p3y, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _p1Base = p1.address() + p1Offset;
        long _p2Base = p2.address() + p2Offset;
        long _p3Base = p3.address() + p3Offset;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _p1x = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p2x = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p3x = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t7) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t5, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t9));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _t0Base = t0.address() + t0Offset;
        long _v1Base = v1.address() + v1Offset;
        long _t1Base = t1.address() + t1Offset;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0y = t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _v1y = v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _t1y = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t0Offset) * _t9) + Math.fma(t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, t1Offset), _t8, v1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, v1Offset) * _t7));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float tX, float tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float tX, float tY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        long _tBase = t.address() + tOffset;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _ty = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        return slerp_api_sc65ceae5_1(dest, destOffset, src, srcOffset, otherX, otherY, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_sc65ceae5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * Math.sin(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t44 = _t20 * Math.cos(_t39);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float2OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return Float2OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = java.lang.Math.min(_t2, _t1);
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_api_s5aca05e6_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, Math.fma(_t47, _t25, _t43), Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_s5aca05e6_1(java.lang.foreign.MemorySegment dest, long destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_api_s5aca05e6_2(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_s5aca05e6_2(java.lang.foreign.MemorySegment dest, long destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t68, float _t69, float _t72, float _t73) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        return slerp_api_s3237ebe1_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t26, _t27, -Math.fma(_t26, _t12, _t27 * _t16));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_s3237ebe1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _t12, float _t16, float _t20, float _t21, float _t26, float _t27, float _t30) {
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * Math.sin(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t44 = _t20 * Math.cos(_t39);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float2OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float2OpsKernelsSegment.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = java.lang.Math.min(_t2, _t1);
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        return slerp_degenerate_api_s7b4d0850_1(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, Math.fma(_t22, _t7, -(_t38 * _t25)), Math.fma(_t22, _t8, -(_t38 * _t27)));
    }

    /** Piece 2 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_s7b4d0850_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t43, float _t44) {
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_api_s7b4d0850_2(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_s7b4d0850_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t68, float _t69, float _t72, float _t73) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment absolute_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.abs(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.acos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.acos(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, bX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(scalar, b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float cX, float cY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(bX, cX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _bBase = b.address() + bOffset;
        long _cBase = c.address() + cOffset;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _by = b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _cy = c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset), c.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, cOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _ct0 = java.lang.Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float2OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _ct0 = java.lang.Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float2OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsSegment.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.asin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.asin(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), xX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, xY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _xBase = x.address() + xOffset;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _xy = x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), x.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, xOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cbrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cbrt(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ceil(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ceil(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min), max));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float maxX, float maxY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float maxX, float maxY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), minX), maxX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _minBase = min.address() + minOffset;
        long _maxBase = max.address() + maxOffset;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _miny = min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset + 4L);
        float _maxy = max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), min.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, minOffset)), max.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, maxOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static float compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static float compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static float compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static float compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), signX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, signY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _signBase = sign.address() + signOffset;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _signy = sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), sign.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, signOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cos(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cos(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosh(_selfy));
        return dest;
    }

    public static float cross_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, otherX, otherY);
    }

    public static float cross_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        return Math.fma(otherY, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), -(otherX * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static float cross_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, _otherBase);
    }

    public static float cross_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), -(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toDegrees(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toDegrees(_selfy));
        return dest;
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _t0 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        return Math.fma(otherX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return Math.fma(other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L) * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.exp(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(2.0f, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(2.0f, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.expm1(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.expm1(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float NrefX, float NrefY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float NrefX, float NrefY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.fma(IX, NrefX, IY * NrefY) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _IBase = I.address() + IOffset;
        long _NrefBase = Nref.address() + NrefOffset;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.fma(I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, NrefOffset + 4L)) < 0.0f ? 1.0f : -1.0f;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _t2);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t2);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.floor(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.floor(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, yY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 1.0f / src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 1.0f / _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        return (float) java.lang.Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static float lengthSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log10(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log10(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log1p(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log1p(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.log2(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log2(_selfy));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - otherX) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY);
    }

    public static float manhattanDistance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L));
    }

    public static float manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        return java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, otherY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.max(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.max(_selfy, _othery));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, otherY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.min(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.min(_selfy, _othery));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, yY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _yBase = y.address() + yOffset;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _yx = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset);
        float _yy = y.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, _yy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextDown(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextDown(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.nextUp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextUp(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t2 = (1.0f / (float) java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t2);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t2);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t3 = length * (1.0f / (float) java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t3);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t3);
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float2OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        long _srcBase = src.address() + srcOffset;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static float orientedAngle_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsSegment.orientedAngle_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float orientedAngle_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float2OpsKernelsSegment.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsSegment.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _otherx = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, rowX * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, rowY * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, rowY * _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _rowBase = row.address() + rowOffset;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment outerProduct_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _rowx = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _rowx * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, _rowy * _selfx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _rowy * _selfy);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponentX));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _exponentBase = exponent.address() + exponentOffset;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _exponenty = exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), exponent.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, exponentOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY) {
        float _t5 = Math.fma(ontoX, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), ontoY * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t5);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _ontoBase = onto.address() + ontoOffset;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        float _ontox = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _t5 = Math.fma(_ontox, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t5);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t5);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t1, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t1, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.toRadians(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toRadians(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = 2.0f * Math.fma(normalX, _selfx, normalY * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _t2 = 2.0f * Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float eta) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(normalX * _t9)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(eta, _selfx, -(_normalx * _t9)));
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment round_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.rint(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.rint(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.signum(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.signum(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sin(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sin(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.sinh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinh(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t7, 3.0f) * _t7 * _t7);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edge0Base = edge0.address() + edge0Offset;
        long _edge1Base = edge1.address() + edge1Offset;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        float _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _edgeBase = edge.address() + edgeOffset;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _edgey = edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) < edge.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edgeOffset) ? 0.0f : 1.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tan(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tan(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.tanh(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tanh(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.ulp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ulp(_selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L) * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMulDirectionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L) * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMulDirectionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulDirectionMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L) * _selfy));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat4x4_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat13 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L), _selfy, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 48L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 8L), _selfy, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _matBase = mat.address() + matOffset;
        Float2OpsKernelsAddress.preMulPositionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulPositionMat3x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _mat10 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat11 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 16L);
        float _mat12 = mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset), _selfx, Math.fma(mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 12L), _selfy, mat.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matOffset + 24L))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - pivotX;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - pivotY;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float angle) {
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) - _pivotx;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _pivoty;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
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
