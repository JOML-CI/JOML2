// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a {@link java.lang.foreign.MemorySegment}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
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
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / scalar);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / scalar);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / scalar);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment div_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / otherX);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / otherY);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / otherZ);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / otherW);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) / other.get(_JAVA_FLOAT_UNALIGNED, otherOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy / _othery);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz / _otherz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw / _otherw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b, cX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, b, cY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, b, cZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, b, cW));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b) + (cX)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (b) + (cY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (b) + (cZ)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (b) + (cW)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b, c.get(_JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, b, _cy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, b, _cz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, b, _cw));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b) + (c.get(_JAVA_FLOAT_UNALIGNED, cOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (b) + (_cy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (b) + (_cz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (b) + (_cw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), bX, cX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, bY, cY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, bZ, cZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, bW, cW));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (bX) + (cX)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (bY) + (cY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (bZ) + (cZ)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (bW) + (cW)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@code fma_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), b.get(_JAVA_FLOAT_UNALIGNED, bOffset), c.get(_JAVA_FLOAT_UNALIGNED, cOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _by, _cy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _bz, _cz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment fma_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) + (c.get(_JAVA_FLOAT_UNALIGNED, cOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_by) + (_cy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_bz) + (_cz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_bw) + (_cw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
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

    public static java.lang.foreign.MemorySegment mul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, otherX * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, otherY * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, otherZ * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, otherW * _selfw);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _othery * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _otherz * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _otherw * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
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
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
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
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
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

    public static java.lang.foreign.MemorySegment makeUniformDirection_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v, float w) {
        long _destBase = dest.address() + destOffset;
        Float4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeUniformDirection_api(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, Math.cosWithSin(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinWithCos(_t1) * _t4);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosWithSin(_t3) * _t0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.sinWithCos(_t3) * _t0);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        return bezier_api_fma_s58540a0d_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0f * _t0 * _t1, 3.0f * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_fma_s58540a0d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset), _t7, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8) + java.lang.Math.fma(p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset), _t6, p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        return bezier_api_mulAdd_sb662e2f6_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, t * _t1, 3.0f * _t0 * _t1, 3.0f * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier_api_mulAdd_sb662e2f6_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset)) * (_t7) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t8)) + ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset)) * (_t6) + (p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@code bezier2_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset), _t0, java.lang.Math.fma(p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset), _t3, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        return bezier2_api_fma_se0df2a48_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_fma_se0df2a48_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t0, float _t3, float _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset)) * (_t0) + (((p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset)) * (_t3) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        return bezier2_api_mulAdd_scc9eadd_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t0, _t3, _t4);
    }

    /** Piece 2 of {@code bezier2_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2_api_mulAdd_scc9eadd_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t0, float _t3, float _t4) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezier2Tangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2X - p1X) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@code bezier2Tangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t2, (p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return bezier2Tangent_api_fma_s7de2b075_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_fma_s7de2b075_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t2) + ((p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset) - _p1x) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        return bezier2Tangent_api_mulAdd_s92f3b85a_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _t1, _t2);
    }

    /** Piece 2 of {@code bezier2Tangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezier2Tangent_api_mulAdd_s92f3b85a_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _t1, float _t2) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment bezierTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t1 = 1.0f - t;
        return bezierTangent_api_fma_s6906978e_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0f * t * t, 6.0f * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_fma_s6906978e_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t1, float _t2, float _t5) {
        float _t6 = 3.0f * _t1 * _t1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        float _t1 = 1.0f - t;
        return bezierTangent_api_mulAdd_sa088275b_1(dest, destOffset, src, srcOffset, p3, p3Offset, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t1, 3.0f * t * t, 6.0f * t * _t1);
    }

    /** Piece 2 of {@code bezierTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment bezierTangent_api_mulAdd_sa088275b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p3, long p3Offset, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t1, float _t2, float _t5) {
        float _t6 = 3.0f * _t1 * _t1;
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRom_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_fma_scdfe0aa5_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_scdfe0aa5_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        return catmullRom_api_mulAdd_see5f3b7a_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_see5f3b7a_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (((2.0f) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W)) * _t1))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRom_api_fma_s5a104abc_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s5a104abc_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_api_fma_s5a104abc_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_fma_s5a104abc_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (java.lang.Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRom_api_mulAdd_sae34f041_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sae34f041_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        return catmullRom_api_mulAdd_sae34f041_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRom_api_mulAdd_sae34f041_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0, float _t1) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * (((2.0f) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) * (_t0) + (((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w)) * _t1))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment catmullRomTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_api_fma_s15a7dbc2_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_s15a7dbc2_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        return catmullRomTangent_api_mulAdd_sb3f3b747_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_sb3f3b747_1(java.lang.foreign.MemorySegment dest, long destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W))) * (_t0) + (p2W - _selfw)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRomTangent_api_fma_scc3087d9_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_scc3087d9_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_api_fma_scc3087d9_2(dest, destOffset, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_fma_scc3087d9_2(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _p1x = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset);
        float _p1y = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 4L);
        float _p1z = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 8L);
        float _p1w = p1.get(_JAVA_FLOAT_UNALIGNED, p1Offset + 12L);
        float _p2x = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset);
        float _p2y = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 4L);
        float _p2z = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 8L);
        float _p2w = p2.get(_JAVA_FLOAT_UNALIGNED, p2Offset + 12L);
        float _p3x = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset);
        float _p3y = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 4L);
        float _p3z = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 8L);
        float _p3w = p3.get(_JAVA_FLOAT_UNALIGNED, p3Offset + 12L);
        return catmullRomTangent_api_mulAdd_s6be7c462_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment catmullRomTangent_api_mulAdd_s6be7c462_1(java.lang.foreign.MemorySegment dest, long destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w) {
        float _t0 = t * t;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) + (((3.0f * ((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermite_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        float _t0 = t * t;
        return hermite_api_fma_sa52a792c_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0f, _t0, t));
    }

    /** Piece 2 of {@code hermite_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_fma_sa52a792c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t2, float _t5, float _t7) {
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t10, t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t7) + java.lang.Math.fma(t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset), _t5, v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        float _t0 = t * t;
        return hermite_api_mulAdd_sbd0e894d_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, t * _t0, t * ((t) * (t) - (t)), ((t - 2.0f) * (_t0) + (t)));
    }

    /** Piece 2 of {@code hermite_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermite_api_mulAdd_sbd0e894d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t2, float _t5, float _t7) {
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t10) + (t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t7)) + ((t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset)) * (_t5) + (v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hermiteTangent_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        return hermiteTangent_api_fma_s18059f1d_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, t * t, 6.0f * java.lang.Math.fma(t, t, -t), 6.0f * java.lang.Math.fma(-t, t, t));
    }

    /** Piece 2 of {@code hermiteTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_fma_s18059f1d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t6, float _t7) {
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _t6, t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t9) + java.lang.Math.fma(t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset), _t8, v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0y = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 4L);
        float _t0z = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 8L);
        float _t0w = t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset + 12L);
        float _v1y = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 4L);
        float _v1z = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 8L);
        float _v1w = v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset + 12L);
        float _t1y = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 4L);
        float _t1z = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 8L);
        float _t1w = t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset + 12L);
        float _t0 = t * t;
        return hermiteTangent_api_mulAdd_sf5aac082_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t0, 6.0f * ((t) * (t) - (t)), 6.0f * ((t) - (t) * (t)), ((3.0f) * (_t0) - (t + t)));
    }

    /** Piece 2 of {@code hermiteTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment hermiteTangent_api_mulAdd_sf5aac082_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t0, float _t6, float _t7, float _t8) {
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (_t6) + (t0.get(_JAVA_FLOAT_UNALIGNED, t0Offset) * _t9)) + ((t1.get(_JAVA_FLOAT_UNALIGNED, t1Offset)) * (_t8) + (v1.get(_JAVA_FLOAT_UNALIGNED, v1Offset) * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
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
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
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

    public static java.lang.foreign.MemorySegment lerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((tY) * (otherY - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((tZ) * (otherZ - _selfz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((tW) * (otherW - _selfw) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@code lerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        float _tw = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t.get(_JAVA_FLOAT_UNALIGNED, tOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment lerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _ty = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        float _tz = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 8L);
        float _tw = t.get(_JAVA_FLOAT_UNALIGNED, tOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t.get(_JAVA_FLOAT_UNALIGNED, tOffset)) * (other.get(_JAVA_FLOAT_UNALIGNED, otherOffset) - _selfx) + (_selfx)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_fma_s830556db_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s830556db_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_s830556db_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s830556db_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s6f89cfdc_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s6f89cfdc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_s6f89cfdc_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s6f89cfdc_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (otherW - _selfw) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_fma_sd47cabcc_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0f / (float) java.lang.Math.sqrt(_t9)), (1.0f / (float) java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sd47cabcc_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t9, float _t10, float _t11, float _t14) {
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        return slerp_api_fma_sd47cabcc_2(dest, destOffset, src, srcOffset, other, otherOffset, t, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sd47cabcc_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t49, float _t50, float _t51, float _t52, float _t57) {
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s67967b81_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, _t11, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11);
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s67967b81_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t9, float _t10, float _t11, float _t14, float _t16) {
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd_s67967b81_2(dest, destOffset, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t49, _t50, _t51, _t52, _t57, t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31));
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s67967b81_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t19, float _t21, float _t24, float _t28, float _t49, float _t50, float _t51, float _t52, float _t57, float _t61) {
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
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
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_fma_sfce6d381_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sfce6d381_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_sfce6d381_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_sfce6d381_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s9202bb60_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s9202bb60_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_s9202bb60_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_s9202bb60_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
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
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_fma_s37ad4c10_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0f / (float) java.lang.Math.sqrt(_t9)));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s37ad4c10_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t9, float _t10, float _t11) {
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        return slerp_api_fma_s37ad4c10_2(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, _t49, _t50, _t51, _t52, java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52))));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_fma_s37ad4c10_2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t49, float _t50, float _t51, float _t52, float _t57) {
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd_sf31643e7_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, _t9, _t10, (1.0f / (float) java.lang.Math.sqrt(_t9)), (1.0f / (float) java.lang.Math.sqrt(_t10)));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sf31643e7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t9, float _t10, float _t11, float _t14) {
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd_sf31643e7_2(dest, destOffset, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t49, _t50, _t51, _t52, _t57, t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31));
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_api_mulAdd_sf31643e7_2(java.lang.foreign.MemorySegment dest, long destOffset, float _t16, float _t19, float _t21, float _t24, float _t28, float _t49, float _t50, float _t51, float _t52, float _t57, float _t61) {
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
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
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_degenerate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t, float epsilon) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.abs(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.abs(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment acos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.acos(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.acos(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.acos(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.acos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(scalar, bX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(scalar, bY, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, bZ, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(scalar, bW, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((scalar) * (bX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((scalar) * (bY) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((scalar) * (bZ) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((scalar) * (bW) + (_selfw)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(scalar, b.get(_JAVA_FLOAT_UNALIGNED, bOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(scalar, _by, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(scalar, _bz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((scalar) * (b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((scalar) * (_by) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((scalar) * (_bz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((scalar) * (_bw) + (_selfw)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(bX, cX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(bY, cY, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(bZ, cZ, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(bW, cW, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((bX) * (cX) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((bY) * (cY) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((bZ) * (cZ) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((bW) * (cW) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@code addScaled_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(b.get(_JAVA_FLOAT_UNALIGNED, bOffset), c.get(_JAVA_FLOAT_UNALIGNED, cOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_by, _cy, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_bz, _cz, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment addScaled_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _by = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 4L);
        float _bz = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 8L);
        float _bw = b.get(_JAVA_FLOAT_UNALIGNED, bOffset + 12L);
        float _cy = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 4L);
        float _cz = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 8L);
        float _cw = c.get(_JAVA_FLOAT_UNALIGNED, cOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((b.get(_JAVA_FLOAT_UNALIGNED, bOffset)) * (c.get(_JAVA_FLOAT_UNALIGNED, cOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_by) * (_cy) + (_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_bz) * (_cz) + (_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        float _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        float _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        float _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        float _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        float _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        return angleBetween_api_fma_s8d1596f7_1(src, srcOffset, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))))));
    }

    /** Piece 2 of {@code angleBetween_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_fma_s8d1596f7_1(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float _selfx, float _selfy, float _selfz, float _selfw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        float _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        float _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        float _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        float _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        float _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        return angleBetween_api_mulAdd_s2021f5c2_1(src, srcOffset, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))))))))));
    }

    /** Piece 2 of {@code angleBetween_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_mulAdd_s2021f5c2_1(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float _selfx, float _selfy, float _selfz, float _selfw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_api_fma_s6906a48e_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_fma_s6906a48e_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_api_mulAdd_sbf043c15_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd_sbf043c15_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static float angleBetween_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_fma(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        return angleBetween_api_fma_sf1a2de56_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, -_otherx * _selfz, java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6), java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7), java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8), java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9), java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10));
    }

    /** Piece 2 of {@code angleBetween_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_fma_sf1a2de56_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t11, float _t24, float _t25, float _t26, float _t27, float _t28) {
        float _t29 = java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11);
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        return angleBetween_api_mulAdd_s810623dd_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6)), ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7)), ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8)), ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9)), ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10)), ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11)));
    }

    /** Piece 2 of {@code angleBetween_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_mulAdd_s810623dd_1(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsSegment.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
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
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        return angleBetween_degenerate_api_fma_s753ecfb1_1(_t16, _t17, _t18, _t19, _t20, _selfx * _t7, _t22, _t23, -_t22 * _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_fma_s753ecfb1_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t31, float _t32, float _t33, float _t34, float _t35, float _t48) {
        float _t49 = java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31);
        float _t50 = java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32);
        float _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _otherx = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        return angleBetween_degenerate_api_mulAdd_s987d4298_1(_t16, _t17, _t18, _t19, _t20, _selfx * _t7, _t22, _t23, -_t22 * _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd_s987d4298_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t31, float _t32, float _t33, float _t34, float _t35, float _t48) {
        float _t49 = ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31));
        float _t50 = ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32));
        float _t51 = ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33));
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static java.lang.foreign.MemorySegment asin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment asin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.asin(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.asin(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.asin(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.asin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), x));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, x));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, x));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ, float xW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment atan2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ, float xW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), xX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, xY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, xZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, xW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _xy = x.get(_JAVA_FLOAT_UNALIGNED, xOffset + 4L);
        float _xz = x.get(_JAVA_FLOAT_UNALIGNED, xOffset + 8L);
        float _xw = x.get(_JAVA_FLOAT_UNALIGNED, xOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.atan2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), x.get(_JAVA_FLOAT_UNALIGNED, xOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.atan2(_selfy, _xy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.atan2(_selfz, _xz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cbrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cbrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cbrt(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cbrt(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cbrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ceil_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.ceil(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ceil(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ceil(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), min), max));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, min), max));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, min), max));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment clamp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), minX), maxX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, minY), maxY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(Math.max(_selfw, minW), maxW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _miny = min.get(_JAVA_FLOAT_UNALIGNED, minOffset + 4L);
        float _minz = min.get(_JAVA_FLOAT_UNALIGNED, minOffset + 8L);
        float _minw = min.get(_JAVA_FLOAT_UNALIGNED, minOffset + 12L);
        float _maxy = max.get(_JAVA_FLOAT_UNALIGNED, maxOffset + 4L);
        float _maxz = max.get(_JAVA_FLOAT_UNALIGNED, maxOffset + 8L);
        float _maxw = max.get(_JAVA_FLOAT_UNALIGNED, maxOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), min.get(_JAVA_FLOAT_UNALIGNED, minOffset)), max.get(_JAVA_FLOAT_UNALIGNED, maxOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)));
    }

    public static float compMax_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return Math.max(Math.max(Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static float compMin_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return Math.min(Math.min(Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static float compMul_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), sign));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, sign));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, sign));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ, float signW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copySign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ, float signW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), signX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, signY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, signZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, signW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _signy = sign.get(_JAVA_FLOAT_UNALIGNED, signOffset + 4L);
        float _signz = sign.get(_JAVA_FLOAT_UNALIGNED, signOffset + 8L);
        float _signw = sign.get(_JAVA_FLOAT_UNALIGNED, signOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.copySign(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), sign.get(_JAVA_FLOAT_UNALIGNED, signOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.copySign(_selfy, _signy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.copySign(_selfz, _signz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cos_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cos(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cos(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cos(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cos(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cosh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.cosh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.cosh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.cosh(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.cosh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment cross_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        return cross_api_fma_sbf518a3b_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t25, _t26, java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9), java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10), java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma_sbf518a3b_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        return cross_api_mulAdd_s875495d2_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11)));
    }

    /** Piece 2 of {@code cross_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd_s875495d2_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t26, float _t27, float _t28, float _t29) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _vw = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        float _wx = w.get(_JAVA_FLOAT_UNALIGNED, wOffset);
        float _wy = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 4L);
        float _wz = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 8L);
        float _ww = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 12L);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        return cross_api_fma_sf7de294d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vz, _vw, _wx, _wy, _wz, _ww, -_vw * _wy, -_vz * _wx, -_vw * _wx, -(_vy * _wx), java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_fma_sf7de294d_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _vx, float _vy, float _vz, float _vw, float _wx, float _wy, float _wz, float _ww, float _t8, float _t9, float _t10, float _t11, float _t24, float _t25) {
        float _t26 = java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8);
        float _t27 = java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9);
        float _t28 = java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10);
        float _t29 = java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _vx = v.get(_JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        float _vw = v.get(_JAVA_FLOAT_UNALIGNED, vOffset + 12L);
        float _wx = w.get(_JAVA_FLOAT_UNALIGNED, wOffset);
        float _wy = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 4L);
        float _wz = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 8L);
        float _ww = w.get(_JAVA_FLOAT_UNALIGNED, wOffset + 12L);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        return cross_api_mulAdd_s99389cc0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vz, _vw, _wx, _wy, _wz, _ww, -_vw * _wy, -_vz * _wx, -_vw * _wx, -(_vy * _wx), ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)));
    }

    /** Piece 2 of {@code cross_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment cross_api_mulAdd_s99389cc0_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _vx, float _vy, float _vz, float _vw, float _wx, float _wy, float _wz, float _ww, float _t8, float _t9, float _t10, float _t11, float _t24, float _t25) {
        float _t26 = ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8));
        float _t27 = ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9));
        float _t28 = ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10));
        float _t29 = ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment degrees_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.toDegrees(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toDegrees(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toDegrees(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distance_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distance_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@code distance_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float distance_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - otherW;
            float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - otherZ;
            float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - otherX;
            float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float distanceSquared_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        long _otherBase = other.address() + otherOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distanceSquared_api_mulAdd(src, srcOffset, other, otherOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
    }

    /** {@code distanceSquared_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float distanceSquared_api_mulAdd(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t0 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
    }

    public static float dot_unsafe(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
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
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
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
        Float4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.exp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.exp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.exp(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.exp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(2.0f, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(2.0f, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(2.0f, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(2.0f, _selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment expm1_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.expm1(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.expm1(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.expm1(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.expm1(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment faceforward_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_fma(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@code faceforward_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = java.lang.Math.fma(I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 12L), Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 12L), java.lang.Math.fma(I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 8L), Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 8L), java.lang.Math.fma(I.get(_JAVA_FLOAT_UNALIGNED, IOffset), Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset), I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 4L)))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment faceforward_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = ((I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 12L)) * (Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 12L)) + (((I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 8L)) * (Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 8L)) + (((I.get(_JAVA_FLOAT_UNALIGNED, IOffset)) * (Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset)) + (I.get(_JAVA_FLOAT_UNALIGNED, IOffset + 4L) * Nref.get(_JAVA_FLOAT_UNALIGNED, NrefOffset + 4L))))))) < 0.0f ? 1.0f : -1.0f;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t4);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment floor_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.floor(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.floor(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.floor(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.floor(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment fract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(_selfw - Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment hypot_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), yX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, yY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, yZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, yW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _yy = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        float _yw = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.hypot(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), y.get(_JAVA_FLOAT_UNALIGNED, yOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.hypot(_selfy, _yy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.hypot(_selfz, _yz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 1.0f / src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 1.0f / _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 1.0f / _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 1.0f / _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment inverseSqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (1.0f / (float) java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.length_unsafe(_srcBase);
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
        return Float4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
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
        Float4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log10_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log10(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log10(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log10(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log10(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log1p_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log1p(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log1p(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log1p(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log1p(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log2_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.log2(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.log2(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.log2(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.log2(_selfw));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L));
    }

    public static float manhattanLength_unsafe(java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        return Float4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        return java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + java.lang.Math.abs(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L));
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment max_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, otherY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, otherZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.max(_selfw, otherW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.max(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.max(_selfy, _othery));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.max(_selfz, _otherz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, scalar));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment min_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), otherX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, otherY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, otherZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(_selfw, otherW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _othery = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 4L);
        float _otherz = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 8L);
        float _otherw = other.get(_JAVA_FLOAT_UNALIGNED, otherOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.min(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), other.get(_JAVA_FLOAT_UNALIGNED, otherOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.min(_selfy, _othery));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.min(_selfz, _otherz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, y));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, y));
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mod_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, yX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, yY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, yZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, yW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _yx = y.get(_JAVA_FLOAT_UNALIGNED, yOffset);
        float _yy = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 4L);
        float _yz = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 8L);
        float _yw = y.get(_JAVA_FLOAT_UNALIGNED, yOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, flooredMod(_selfx, _yx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, flooredMod(_selfy, _yy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, flooredMod(_selfz, _yz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextDown_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.nextDown(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextDown(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextDown(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.nextDown(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nextUp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.nextUp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.nextUp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.nextUp(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.nextUp(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
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

    public static java.lang.foreign.MemorySegment normalizeMul_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalizeMul_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_api_mulAdd(dest, destOffset, src, srcOffset, length);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment normalizeMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz * _t5);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw * _t5);
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, rowX * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, rowX * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, rowX * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, rowX * _selfw);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, rowY * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, rowY * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, rowY * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, rowY * _selfw);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, rowZ * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, rowZ * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 40L, rowZ * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 44L, rowZ * _selfw);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _rowx = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset);
        float _rowy = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 4L);
        float _rowz = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 8L);
        float _roww = row.get(_JAVA_FLOAT_UNALIGNED, rowOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _rowx * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _rowx * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _rowx * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _rowx * _selfw);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 16L, _rowy * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 20L, _rowy * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 24L, _rowy * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 28L, _rowy * _selfw);
        return outerProduct_api_s908a9879_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _rowz, _roww);
    }

    /** Piece 2 of {@code outerProduct_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment outerProduct_api_s908a9879_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _rowz, float _roww) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 32L, _rowz * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 36L, _rowz * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 40L, _rowz * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 44L, _rowz * _selfw);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 48L, _roww * _selfx);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 52L, _roww * _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 56L, _roww * _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 60L, _roww * _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponent));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponent));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponent));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment pow_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponentX));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, exponentY));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, exponentZ));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, exponentW));
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _exponenty = exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset + 4L);
        float _exponentz = exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset + 8L);
        float _exponentw = exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.pow(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), exponent.get(_JAVA_FLOAT_UNALIGNED, exponentOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.pow(_selfy, _exponenty));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.pow(_selfz, _exponentz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment project_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t9 = java.lang.Math.fma(ontoW, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), java.lang.Math.fma(ontoZ, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(ontoX, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), ontoY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ontoW * _t9);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t9 = ((ontoW) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) + (((ontoZ) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((ontoX) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (ontoY * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ontoX * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ontoY * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ontoZ * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ontoW * _t9);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _ontox = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _ontow = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 12L);
        float _t9 = java.lang.Math.fma(_ontow, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), java.lang.Math.fma(_ontoz, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), java.lang.Math.fma(_ontox, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), _ontoy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _ontow * _t9);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment project_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _ontox = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset);
        float _ontoy = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 4L);
        float _ontoz = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 8L);
        float _ontow = onto.get(_JAVA_FLOAT_UNALIGNED, ontoOffset + 12L);
        float _t9 = ((_ontow) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) + (((_ontoz) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) + (((_ontox) * (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) + (_ontoy * src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _ontox * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _ontoy * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _ontoz * _t9);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _ontow * _t9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment projectOnPlane_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-normalY, _t3, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalZ, _t3, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (normalY) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (normalZ) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) - (normalW) * (_t3)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment projectOnPlane_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (_normaly) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (_normalz) * (_t3)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) - (_normalw) * (_t3)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment radians_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.toRadians(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.toRadians(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.toRadians(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.toRadians(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = 2.0f * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-normalX, _t4, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-normalY, _t4, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-normalZ, _t4, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = 2.0f * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (normalX) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (normalY) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (normalZ) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) - (normalW) * (_t4)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = 2.0f * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-_normalx, _t4, _selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-_normaly, _t4, _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-_normalz, _t4, _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = 2.0f * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) - (_normalx) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) - (_normaly) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) - (_normalz) * (_t4)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.lang.foreign.MemorySegment refract_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return refract_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@code refract_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((eta) * (_selfx) - (normalX * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((eta) * (_selfy) - (normalY * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((eta) * (_selfz) - (normalZ * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return refract_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@code refract_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        return refract_api_fma_sa10407c3_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
    }

    /** Piece 2 of {@code refract_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_fma_sa10407c3_1(java.lang.foreign.MemorySegment dest, long destOffset, float eta, float _selfx, float _selfy, float _selfz, float _selfw, float _normalx, float _normaly, float _normalz, float _normalw, float _t8, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t8 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _normalx = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normaly = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _normalz = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normalw = normal.get(_JAVA_FLOAT_UNALIGNED, normalOffset + 12L);
        float _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        return refract_api_mulAdd_s60369898_1(dest, destOffset, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8)))));
    }

    /** Piece 2 of {@code refract_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment refract_api_mulAdd_s60369898_1(java.lang.foreign.MemorySegment dest, long destOffset, float eta, float _selfx, float _selfy, float _selfz, float _selfw, float _normalx, float _normaly, float _normalz, float _normalw, float _t8, float _t11) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (_t8 >= 0.0f) {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((eta) * (_selfx) - (_normalx * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((eta) * (_selfy) - (_normaly * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((eta) * (_selfz) - (_normalz * _t11)));
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((eta) * (_selfw) - (_normalw * _t11)));
        } else {
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, 0.0f);
            dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, 0.0f);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.rint(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.rint(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.rint(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.rint(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sign_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.signum(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.signum(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.signum(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.signum(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sin_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.sin(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sin(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sin(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.sin(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sinh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.sinh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.sinh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.sinh(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.sinh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0) * _t0_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0) * _t0_inv));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.lang.foreign.MemorySegment smoothstep_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0W) / (edge1W - edge0W)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - edge0W) / (edge1W - edge0W)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        float _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 12L);
        return smoothstep_api_fma_sda050f3b_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_fma_sda050f3b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, float _edge0x, float _edge0y, float _edge0z, float _edge0w) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - _edge0w) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 12L) - _edge0w)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        float _edge0x = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset);
        float _edge0y = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 4L);
        float _edge0z = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 8L);
        float _edge0w = edge0.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, edge0Offset + 12L);
        return smoothstep_api_mulAdd_sca174fee_1(dest, destOffset, src, srcOffset, edge1, edge1Offset, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment smoothstep_api_mulAdd_sca174fee_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge1, long edge1Offset, float _edge0x, float _edge0y, float _edge0z, float _edge0w) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) - _edge0x) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) - _edge0y) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 4L) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) - _edge0z) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 8L) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) - _edge0w) / (edge1.get(_JAVA_FLOAT_UNALIGNED, edge1Offset + 12L) - _edge0w)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sqrt_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, (float) java.lang.Math.sqrt(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, (float) java.lang.Math.sqrt(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, (float) java.lang.Math.sqrt(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edge ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edge ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edge ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment step_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < edgeW ? 0.0f : 1.0f);
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
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _edgey = edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset + 4L);
        float _edgez = edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset + 8L);
        float _edgew = edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) < edge.get(_JAVA_FLOAT_UNALIGNED, edgeOffset) ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tan_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.tan(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tan(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tan(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.tan(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment tanh_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.tanh(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.tanh(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.tanh(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.tanh(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment trunc_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.lang.foreign.MemorySegment ulp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, Math.ulp(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, Math.ulp(_selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, Math.ulp(_selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, Math.ulp(_selfw));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat30 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat31 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat32 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMul_api_fma_s24c057fd_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_fma_s24c057fd_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat10, float _mat20, float _mat30, float _mat11, float _mat21, float _mat31, float _mat12, float _mat22, float _mat32, float _mat13, float _mat23) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _mat33 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 60L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L), _selfw, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L), _selfz, java.lang.Math.fma(mat.get(_JAVA_FLOAT_UNALIGNED, matOffset), _selfx, mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _mat10 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 4L);
        float _mat20 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 8L);
        float _mat30 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 12L);
        float _mat11 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 20L);
        float _mat21 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 24L);
        float _mat31 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 28L);
        float _mat12 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 36L);
        float _mat22 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 40L);
        float _mat32 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 44L);
        float _mat13 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 52L);
        float _mat23 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 56L);
        return preMul_api_mulAdd_s79fccc84_1(dest, destOffset, mat, matOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preMul_api_mulAdd_s79fccc84_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment mat, long matOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat10, float _mat20, float _mat30, float _mat11, float _mat21, float _mat31, float _mat12, float _mat22, float _mat32, float _mat13, float _mat23) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _mat33 = mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 60L);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 48L)) * (_selfw) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 32L)) * (_selfz) + (((mat.get(_JAVA_FLOAT_UNALIGNED, matOffset)) * (_selfx) + (mat.get(_JAVA_FLOAT_UNALIGNED, matOffset + 16L) * _selfy)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset);
        return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@code rotate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        return rotate_api_fma_se326eea1_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_fma_se326eea1_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _quatx, float _quaty, float _quatw, float _t9, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotate_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@code rotateAxis_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        return rotateAxis_api_fma_s2940bfe9_1(dest, destOffset, _axisx, _axisy, _axisz, _selfx, _selfy, _selfz, _selfw, _t0, _t1, _t2, _t5);
    }

    /** Piece 2 of {@code rotateAxis_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_fma_s2940bfe9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _axisx, float _axisy, float _axisz, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t1, float _t2, float _t5) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateAxis_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _axisx = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisy = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisz = axis.get(_JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateInverse_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_fma(dest, destOffset, src, srcOffset, quat, quatOffset);
        return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@code rotateInverse_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _quatx = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset);
        float _quaty = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 4L);
        float _quatz = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 8L);
        float _quatw = quat.get(_JAVA_FLOAT_UNALIGNED, quatOffset + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        return rotateInverse_api_fma_s8c83a4c9_1(dest, destOffset, _selfz, _selfw, _quatx, _quaty, _quatw, _t9, _t10, _t11);
    }

    /** Piece 2 of {@code rotateInverse_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_fma_s8c83a4c9_1(java.lang.foreign.MemorySegment dest, long destOffset, float _selfz, float _selfw, float _quatx, float _quaty, float _quatw, float _t9, float _t10, float _t11) {
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateInverse_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
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
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateX_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateX_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateX_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, src.get(_JAVA_FLOAT_UNALIGNED, srcOffset));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfy) * (_t0) - (_selfz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfy) * (_t1) + (_selfz * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateY_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateY_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateY_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, _selfy);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, ((_selfz) * (_t0) - (_selfx * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        Float4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZ_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
        return dest;
    }

    /** {@code rotateZ_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment rotateZ_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 4L, ((_selfx) * (_t1) + (_selfy * _t0)));
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 8L, _selfz);
        dest.set(_JAVA_FLOAT_UNALIGNED, destOffset + 12L, _selfw);
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
